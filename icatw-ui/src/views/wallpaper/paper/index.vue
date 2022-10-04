<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="壁纸名" prop="paperName">
        <el-input
          v-model="queryParams.paperName"
          placeholder="请输入壁纸名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否启用">
        <template>
          <el-select v-model="queryParams.isDeleted" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['wallpaper:paper:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wallpaper:paper:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wallpaper:paper:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['wallpaper:paper:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="paperList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="壁纸id" align="center" prop="paperId" />
      <el-table-column label="壁纸名" align="center" prop="paperName" />
      <el-table-column label="壁纸" align="center" prop="paperUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.paperUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否启用" align="center" prop="isDeleted" >
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.isDeleted"
              :active-value="0"
              :inactive-value="1"
              @change="handleStatusChange(scope.row)"
            ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="type.typeName" ></el-table-column>
      <el-table-column label="图片分辨率" align="center" prop="paperSize" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['wallpaper:paper:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['wallpaper:paper:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改壁纸列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="壁纸名" prop="paperName">
          <el-input v-model="form.paperName" placeholder="请输入壁纸名" />
        </el-form-item>
        <el-form-item label="请选择分类">
          <template>
            <el-select v-model="form.typeId" placeholder="请选择分类">
              <el-option
                v-for="item in typeList"
                :key="item.typeId"
                :label="item.typeName"
                :value="item.typeId">
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="壁纸">
          <image-upload v-model="form.paperUrl"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listPaper, getPaper, delPaper, addPaper, updatePaper, changePaperStatus} from "@/api/wallpaper/paper";
import { listAllType } from "@/api/wallpaper/type";

export default {
  name: "Paper",
  data() {
    return {
      options: [{
        value: '0',
        label: '启用'
      }, {
        value: '1',
        label: '禁用'
      }],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 壁纸列表表格数据
      paperList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        paperName: null,
        paperUrl: null,
        createTime: null,
        updateTime: null,
        isDeleted: null,
        typeId: null,
      },
      // 表单参数
      form: {},
      typeList:[{
        typeId:'',
        typeName:''
      }],
      // 表单校验
      rules: {
        paperName: [
          { required: true, message: "壁纸名不能为空", trigger: "blur" }
        ],
        paperUrl: [
          { required: true, message: "壁纸url不能为空", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "分类id不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getTypeList()
  },
  methods: {
    /** 查询壁纸列表列表 */
    getList() {
      this.loading = true;
      listPaper(this.queryParams).then(response => {
        this.paperList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //获取分类列表
    getTypeList() {
      listAllType().then(response => {
        console.log(response.data)
        console.log(response)
        this.typeList=response.data
      })
    },
    // 壁纸状态修改
    handleStatusChange(row) {
      let text = row.isDeleted === "0" ? "启用" : "停用";
      this.$modal.confirm('确认要"' + text + '""' + row.paperName + '"壁纸吗？').then(function () {
        return changePaperStatus(row.paperId, row.isDeleted);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function () {
        row.paperName = row.paperName === "0" ? "1" : "0";
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        paperId: null,
        paperName: null,
        paperUrl: null,
        createTime: null,
        updateTime: null,
        isDeleted: null,
        typeId: null,
        paperSize: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.paperId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加壁纸列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const paperId = row.paperId || this.ids
      getPaper(paperId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改壁纸列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.paperId != null) {
            updatePaper(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPaper(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const paperIds = row.paperId || this.ids;
      this.$modal.confirm('是否确认删除壁纸列表编号为"' + paperIds + '"的数据项？').then(function() {
        return delPaper(paperIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('wallpaper/paper/export', {
        ...this.queryParams
      }, `paper_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

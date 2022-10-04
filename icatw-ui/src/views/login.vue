<template>
  <div class="login-container">
    <div class="login-card">
      <div class="login-title">管理员登录</div>
      <!-- 登录表单 -->
      <el-form
        status-icon
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        ref="loginForm"
      >
        <!-- 用户名输入框 -->
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            prefix-icon="el-icon-user-solid"
            placeholder="用户名"
          />
        </el-form-item>
        <!-- 密码输入框 -->
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            prefix-icon="iconfont el-icon-mymima"
            show-password
            placeholder="密码"
            auto-complete="off"
            type="password"
            @keyup.enter.native="handleLogin"
          />
        </el-form-item>
        <Verify
          @success="capctchaCheckSuccess"
          :mode="'pop'"
          :captchaType="'blockPuzzle'"
          :imgSize="{ width: '330px', height: '155px' }"
          ref="verify"
        ></Verify>
        <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      </el-form>
      <!-- 登录按钮 -->
      <el-button :loading="loading" type="primary" @click.native.prevent="handlelLogin">
        <span v-if="!loading">登 录</span>
        <span v-else>登 录 中...</span>
      </el-button>
    </div>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import {encrypt, decrypt} from "@/utils/jsencrypt";
import {Message} from 'element-ui';

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
      },
      loginRules: {
        username: [
          {required: true, trigger: "blur", message: "请输入您的账号"}
        ],
        password: [
          {required: true, trigger: "blur", message: "请输入您的密码"}
        ],
      },
      loading: false,
      // 注册开关
      register: false,
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCookie();
  },
  methods: {
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get("rememberMe");
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    capctchaCheckSuccess(params) {
      this.loginForm.code = params.captchaVerification;
      this.loading = true;
      if (this.loginForm.rememberMe) {
        Cookies.set("username", this.loginForm.username, {expires: 30});
        Cookies.set("password", encrypt(this.loginForm.password), {expires: 30,});
        Cookies.set("rememberMe", this.loginForm.rememberMe, {expires: 30});
      } else {
        Cookies.remove("username");
        Cookies.remove("password");
        Cookies.remove("rememberMe");
      }
      this.$store.dispatch("Login", this.loginForm).then(() => {
        this.$router.push({path: this.redirect || "/"}).catch(() => {
        });
      }).catch(() => {
        this.loading = false;
      });
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.$refs.verify.show();
        }
      });
    },
    handlelLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          let captcha = new TencentCaptcha('2038488304', (rsp) => {
            if (rsp.ret === 0) {
              if (this.loginForm.rememberMe) {
                Cookies.set("username", this.loginForm.username, {expires: 30});
                Cookies.set("password", encrypt(this.loginForm.password), {expires: 30,});
                Cookies.set("rememberMe", this.loginForm.rememberMe, {expires: 30});
              } else {
                Cookies.remove("username");
                Cookies.remove("password");
                Cookies.remove("rememberMe");
              }
              this.$store.dispatch("Login", this.loginForm).then(() => {
                this.$router.push({path: this.redirect || "/"}).then(() => {
                  var aData = new Date();
                  console.log(aData) //Wed Aug 21 2019 10:00:58 GMT+0800 (中国标准时间)

                  this.value =
                    aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate()+'-'+aData.getHours() + ':' + aData.getMinutes();
                  console.log(this.value) //2019-8-20
                  this.$notify({
                    title: '欢迎' + this.loginForm.username + '来到icatwの后台管理系统',
                    message: "详情访问 <a href='https://gitee.com/icatw/Ruoyi-vue'>" +
                      "https://gitee.com/icatw/Ruoyi-vue</a><br>" + this.value,
                    type: 'success',
                    duration: '4000',
                    dangerouslyUseHTMLString: true,
                  });
                }).catch(() => {

                });
              }).catch(() => {
                this.loading = false;
              });
            } else {
              this.loading = false;
              Message.error("请重新验证！")
            }
          });
          captcha.show();
        }
      });

    },
  },
};
</script>

<style scoped>
.login-container {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background: url(https://picgo-iamges.oss-cn-hangzhou.aliyuncs.com/img/202205301231504.png) center center /
    cover no-repeat;
}

.login-card {
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  background: #fff;
  padding: 170px 60px 180px;
  width: 350px;
}

.login-title {
  color: #303133;
  font-weight: bold;
  font-size: 1rem;
}

.login-form {
  margin-top: 1.2rem;
}

.login-card button {
  margin-top: 1rem;
  width: 100%;
}
</style>

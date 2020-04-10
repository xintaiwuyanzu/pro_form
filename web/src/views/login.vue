<template>
    <div class="login"  >
        <div class="bg">
            <el-row>
                <el-col :span="16">
                    <div class="bg-1">
                    </div>
                </el-col>
                <el-col :span="8" style="margin-top: 7vh">
                    <img src="@/assets/logo.png" style="margin-left: 2vw;height: 5vh;width: 18vw"  />
                    <div class="form" @keyup.enter="doLogin(form)">
                        <el-form>
                            <el-form-item  >
                                <el-input style=" margin-top:5vh;margin-left:2.5vw;width: 17vw"  type="text" placeholder="请输入用户名" v-model="form.username" :disabled="loginLoading" autofocus>
                                </el-input>
                            </el-form-item>
                            <el-form-item >
                                <el-input style="width: 17vw;margin-left:2.5vw;"  placeholder="请输入密码" v-model="form.password" type="password" :disabled="loginLoading">
                                </el-input>
                            </el-form-item>
                            <div class="el-input-group">
                                <label style="color: white">记住我？</label>
                                <el-switch v-model="form.remberpwd" active-text="" inactive-text="">
                                </el-switch>
                            </div>
                            <!--<el-button type="primary"   :loading="loginLoading" @click="doLogin(form)">登&nbsp;&nbsp;&nbsp;陆</el-button>-->
                            <img src="@/assets/login.png" style="margin-left: 2.5vw;height: 5vh;width: 17vw" @click="doLogin(form)" />
                        </el-form>
                    </div>

                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
  export default {
    data () {
      return {
        form: {
          username: '',
          password: '',
          remberpwd: false
        }
      }
    }
  }
</script>
<style lang="scss">
    .login {
        background: linear-gradient(114deg, #1076d6, #003781);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;

        img {
            max-width: 100vw;
            height: auto;
            background-size:cover;
        }

        .bg{
            background-image: url("../assets/bg1.png");
            height: 70vh;
            width: 80vw;
            align-content: center;
            background-size:cover ;

            .bg-1{
                background-image: url("../assets/bg2.png");
                height: 45vh;
                width: 30vw;
                margin-top: 10vh;
                margin-left: 5vw;
                background-size:cover;
            }

            .form {
                background-image: url("../assets/loginbg.png");
                width: 22vw;
                height: 40vh;
                background-size:cover;
                .el-input-group {
                    margin-bottom: 2vh;
                    margin-left:13vw;
                }
                .el-input__inner{
                    height: 5vh;
                }
            }

        }

    }
</style>

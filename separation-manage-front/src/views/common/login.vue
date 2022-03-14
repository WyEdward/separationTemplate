<template>
    <div class="site-wrapper site-page--login">
        <div class="site-content__wrapper">
            <div class="site-content">
                <div class="brand-info">
                    <h2 class="brand-info__text">宁波萨瑞通讯</h2>
                    <p class="brand-info__intro">管理系统</p>
                </div>
                <div class="login-main">
                    <h3 class="login-title">用户登录</h3>
                    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" status-icon>
                        <el-form-item prop="userName">
                            <el-input v-model="dataForm.userName" placeholder="帐号"></el-input>
                        </el-form-item>
                        <el-form-item prop="password">
                            <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button  class="login-btn-submit" type="primary" @click="dataFormSubmit()">登录</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        data () {
            return {
                dataForm: {
                    userName: '',
                    password: '',
                    rememberMe: false
                },
                dataRule: {
                    userName: [
                        { required: true, message: '帐号不能为空', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '密码不能为空', trigger: 'blur' }
                    ]
                }
            }
        },
        mounted(){

        },
        created () {

        },
        computed:{
            ...mapState('currentUser', ['token', 'userName'])
        },
        methods: {
            // 提交表单
            async dataFormSubmit () {
                let params={
                    userName: this.dataForm.userName,
                    password: this.dataForm.password,
                    rememberMe: this.dataForm.rememberMe
                };
                let resultContent = await this.$api.login.login(params);
                let result = resultContent.data;
                console.log(result);
                if(result.code === 200){
                    this.$message.success("登录成功");
                    if(sessionStorage.getItem('userName') === null){
                        /*console.log('比较1-------------')
                        console.log(sessionStorage.getItem('userName'));
                        console.log(this.dataForm.userName);*/
                        let permissionList = result.permissionList;
                        permissionList = permissionList.map((item)=>{return item.permissionName});
                        sessionStorage.setItem("permissionList", permissionList);
                        this.$store.commit('currentUser/setToken', result.token);
                        this.$store.commit('currentUser/setUserName', this.dataForm.userName);
                        this.$store.commit('currentUser/setNickName', result.nickName);
                        this.$store.commit('currentUser/setUserId', result.userId);
                        if(!this.$store.state.permission.isFlesh){
                            this.$store.dispatch('permission/init').then(()=>{});
                            this.$store.commit('permission/SET_IS_FLESH', 1);
                        }
                        this.$router.push({
                            path:'/home'
                        });
                    }else if(sessionStorage.getItem('userName') === this.dataForm.userName){
                        this.$message.success("您已登录");
                        if(!this.$store.state.permission.isFlesh){
                            this.$store.dispatch('permission/init').then(()=>{console.log('获取用户信息成功3')});
                            this.$store.commit('permission/SET_IS_FLESH', 1);
                        }
                        this.$router.push({
                            path:'/home'
                        });
                    }else{
                        this.$message.error("本地只能登一个账号，请退出当前账号");
                    }
                }else{
                    this.$message.error("登录失败");
                }
            }
        }
    }
</script>

<style lang="scss">
    .site-wrapper.site-page--login {
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background-color: rgba(38, 50, 56, .6);
        overflow: hidden;
        &:before {
            position: fixed;
            top: 0;
            left: 0;
            z-index: -1;
            width: 100%;
            height: 100%;
            content: "";
            //background-image: url();
            background-size: cover;
        }
        .site-content__wrapper {
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            padding: 0;
            margin: 0;
            overflow-x: hidden;
            overflow-y: hidden;
            background-color: transparent;
        }
        .site-content {
            min-height: 100%;
            padding: 30px 500px 30px 30px;
        }
        .brand-info {
            margin: 220px 100px 0 90px;
            color: #fff;
        }
        .brand-info__text {
            margin:  0 0 22px 0;
            font-size: 48px;
            font-weight: 400;
        }
        .brand-info__intro {
            margin: 10px 0;
            font-size: 18px;
            line-height: 1.58;
            opacity: .7;
        }
        .login-main {
            position: absolute;
            top: 0;
            right: 0;
            padding: 150px 60px 180px;
            width: 470px;
            min-height: 100%;
            background-color: #fff;
        }
        .login-title {
            font-size: 16px;
        }
        .login-captcha {
            overflow: hidden;
            > img {
                width: 100%;
                cursor: pointer;
            }
        }
        .login-btn-submit {
            width: 100%;
            margin-top: 38px;
        }
    }
</style>

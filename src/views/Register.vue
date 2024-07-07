<template>
    <div class="container">
        <div class="background"></div> 
        <div class='register'>
        <div class="title">
            <h1>注册新用户</h1>
            <div class="main">
            <div class="left">
            <el-form ref="registerForm" :model="registerForm" :rules="rules" label-width="0px" 
            @submit.prevent="submitForm('registerForm')">
      <el-form-item label="" prop="username">
        <el-input v-model="registerForm.username" clearable placeholder="请输入用户名"></el-input>
      </el-form-item>
      
      <el-form-item label="" prop="password">
        <el-input v-model="registerForm.password" clearable placeholder="请输入密码" show-password>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="email">
        <el-input v-model="registerForm.email" clearable placeholder="请输入电子邮箱"></el-input>
      </el-form-item>
        <div class="right">
        <span>请上传头像</span>
  <el-upload
  class="avatar-uploader"
  action="http://localhost:8090/upload"
  :show-file-list="false"
  :on-success="handleAvatarSuccess"
  :before-upload="beforeAvatarUpload">
  <img v-if="registerForm.imageUrl" :src="'http://localhost:8090/images/upload/'+registerForm.imageUrl" 
  class="avatar">
  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>

</div>
    </el-form>
</div>
     </div>
     <div class="foot">
        <el-button  native-type="submit" class="sub"  @click="submitForm('registerForm')">
          注册用户</el-button>
        <el-button class="return-btn sub"  @click="returnLogin" >返回登录</el-button>
     </div>
    </div>
</div>

      </div>
  </template>
  
<script>
 export default {
  data() {
    return {
      registerForm: {
        username: '',
        email: '',
        password: '',
        imageUrl:''

      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        email: [
          { required: true, message: '请输入电子邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入有效的电子邮箱地址', trigger: ['blur', 'change'] }
        ],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
      }
    };
  },
  methods: {
    handleAvatarSuccess(res) {
      console.log("img",res)
      this.registerForm.imageUrl= res
        //this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios.get('http://localhost:8090/register', { 
            params:{
        username:this.registerForm.username,
        email:this.registerForm.email,
        password:this.registerForm.password,
        imageurl:this.registerForm.imageUrl
    }
})
.then(response => {
    console.log(response);
    if(response.data=='Y'){
      this.$message({message:"注册成功",type:'warning'});    
    } 
    else this.$message({message:"用户已存在",type:'warning'});
  }).catch((error) => {
  console.log(error);
});
        } else {
          // 表单验证不通过
          console.log('表单验证失败');
          return false;
        }
      });
    },
    returnLogin(){
        if(this.$route.path!=='/'){
          this.$router.push('/')
        }
      },
  }
};
  </script>
<style scoped>
* {
    margin: 0;
    padding: 0
}
.title {
    text-align: center; 
    margin-bottom: 20px; 
}
.register {
    width: 35%; 
    height: 45%;
    background-color: #f2f2f2; 
    padding: 20px; 
    border-radius: 8px; /* 设置圆角 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
}
.title h1 {
    font-size: 24px; 
    color: #333; 
}
.background {
    background: url('../assets/background.jpg') center top no-repeat;
    width: 100%;
        height: 100%;
        position:fixed;
    background-size:100% 100%;
    overflow: hidden;
    z-index: -1;
}
.main {
    display: flex; /* 使用 Flex 布局 */
    justify-content: space-between; /* 项目之间平均分布 */
    margin-bottom: 15px; 
    position: relative; /* 相对定位，用于画竖线 */
}
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  
}
.main::after {
    content: '';
    position: absolute;
    top: 30px;
    bottom: 10px;
    left: 50%; /* 位于中间 */
    width: 1px; /* 竖线宽度 */
    background-color: #ccc; /* 竖线颜色 */
    transform: translateX(-50%); /* 将竖线向左平移50% */
}
/* .main {
    
  max-width: 400px; 
  padding: 20px; 
  background-color: #f5f5f5; 
  border-radius: 5px; 
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
  opacity: 0.9;
width: 350px;
  display: flex;
  flex-direction: column;
  align-items: center;
} */



  /* 输入框容器 */
  .el-form-item {
    margin-bottom: 22px;
  }
  
  /* 提交按钮 */
  .el-button {
    width: 100%;
  }
  
  /* 警告框 */
  .el-alert {
    margin-top: 20px;
  }
  /* .return-btn {
  margin-top: 10px; 
  color: white; 
  width: 100%;
  background-color: #505050;
}
.return-btn:hover {
  background-color: #D3DCE6;
} */
.left,
.right {
    width: calc(50% - 10px); /* 宽度设置为50%减去10px的间距 */
    /* margin-right:20px; */
    margin-top: 50px; 
    
}
.right{
  margin-top: 35px; 
}
.span{
    margin-bottom: 20px;
}

.left {
    display: flex; /* 使用 Flex 布局 */
    justify-content: center; /* 水平居中 */
    align-items: center; /* 垂直居中 */
    flex-direction: column; /* 垂直方向排列 */
    
}


.right {
  position: absolute;
    top: 0;
    right: 0;
  
}
.foot{
    background-color: #494949;
    display: flex;
    height: 50px;
    text-align: center;
    margin-top: 0px;
    
}
.sub {
    background-color: #505458;
    color: white;
    width: 350px;
    height: 100%;
    border: none;
    float: left;
}
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 170px;
    line-height: 170px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 170px;
    display: block;
  }



@media (max-width: 800px){

.title{
  height: 100%;
}
.register{
    width: 60%;
    height: 80%;
}
.main{
  display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px;
    height: 80%; 
}
.main::after {
    display: none;
}
.left{
   
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 10px;
}
.right {
    position: relative; /* 设置为相对定位 */
    top: 20px; /* 调整位置 */
    width: 60%;
    height: 150px;
    margin-top: 0px; /* 设置与表单的垂直距离 */
  }
#img_div{
    width: 100%;
    height: 50%;
}
.left{
    width: 60%;
}

.foot{
  margin-bottom: 20px;
}

.avatar-uploader {
    width: 120px !important; /* 设置头像框的宽度 */
    height: 20px !important; /* 设置头像框的高度 */

  }

  .avatar {
    width: 100% !important; /* 设置头像的宽度 */
    height: 80% !important; /* 设置头像的高度 */
    object-fit: cover !important; /* 填充整个框 */
  }

}

/* @media (max-width: 800px) {
  .register {
    width: 60%;
    height: 80%;
  }
  
  .main {
    display: flex;
    flex-direction: column;
    align-items: center;
    align-content: space-between;
    margin-bottom: 20px;
  }
  
  .main::after {
    display: none;
  }
  
  .left {
    margin-top: 20px;
    width: 60%;
  }
  
  .right {
    position: relative; 
    top: 10px; 
    height: 150px;
    width: 40%;
  }

  .register {
  position: relative;
}

.foot {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 50px; 
  background-color: #494949;
  display: flex;
  justify-content: space-between;
  padding: 0 20px;
  box-sizing: border-box;
} */



  


  </style>
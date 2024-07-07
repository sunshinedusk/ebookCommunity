<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import Login from './views/Login.vue'

export default{
  name :'app',
  components:{
     Login
  }
}

</script>
<style>
html,body,#app{
    margin: 0;
    padding: 0;
    height: 100%;
    
    /* background-color: lightgray; */
    overflow-y: hidden;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
</style>

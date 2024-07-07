<!-- <template>
    <div>123</div>
</template>
  
  <script>
    export default {
      name: 'AppIndex',
      components: {}
    }
  </script>
  
  <style scoped>

  </style> -->
  
  <template>
    
    <div class="carousel-wrapper">
         <el-carousel :interval="4000" height="400px" width="250px" @change="changeImg" indicator-position="outside">
            <el-carousel-item v-for="(item,index) in images" :key="index">
            <el-image
                :class="className"
                style="width:100%; height:100%;"
                :src="item.idView"
                fit="fill"></el-image>
            </el-carousel-item>
            
      
        </el-carousel>
    </div>
</template>
<script>
export default {
    name: 'AppIndex',
    data() {
        return {
            images: [
                {id: 0, idView: require('../assets/1.jpg')},
                {id: 1, idView: require('../assets/2.jpg')},
                {id: 2, idView: require('../assets/3.jpg')},
                {id: 3, idView: require('../assets/4.jpg')},
                {id: 4, idView: require('../assets/5.jpg')},
            ],
            className: "",
        }
    },
    mounted() {
        this.className = "lun-img-two";
        setTimeout(() => {
            this.className = "lun-img";
        }, 300);
    },
    methods: {
        changeImg: function (e) {
            this.className = "lun-img-two";
            setTimeout(() => {
                this.className = "lun-img";
            }, 300);
        }
    }
}
</script>
<style scoped>
.carousel-wrapper {
  margin-top: 50px;
  margin-left: 50px;
  width: 400px; 
  height: 300px; 
  
}
    .lun-img {
        transition: all 4s;
        transform: scale(1.1);
    }
    .lun-img-two {
        transform: scale(1);
    }
    .el-carousel__item.is-animating {
        transition: transform 1s ease-in-out;
    }
    .indicator-item {
  width: 12px;
  height: 12px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.5); 
  margin: 0 8px; 
}


</style>

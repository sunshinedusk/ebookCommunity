<!-- <template>
  <div>
    <el-row style="height: 600px;">
      <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>
      <view-switch class="switch"></view-switch>
      <el-tooltip effect="dark" placement="right"
                  v-for="item in books.slice((pagination.currentPage-1)*pagination.pageSize,
                  pagination.currentPage*pagination.pageSize)"
                  :key="item.id">
        <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.title}}</p>
        <p slot="content" style="font-size: 13px;margin-bottom: 6px">
          <span>{{item.author}}</span> /
          <span>{{item.date}}</span> /
          <span>{{item.press}}</span>
        </p>
        <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
        <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                 bodyStyle="padding:10px" shadow="hover">
          <div class="cover">
            <img :src="item.cover" alt="封面">
          </div>
          <div class="info">
            <div class="title">
              <a href="">{{item.title}}</a>
            </div>
          </div>
          <div class="author">{{item.author}}</div>
        </el-card>
      </el-tooltip>
    </el-row>

    <el-row>
      <el-pagination
      background
      layout="total, prev, pager, next, jumper"
      @current-change="handleCurrentChange"
      :page-size="pagination.pageSize"
      :current-page="pagination.currentPage"
      :total="pagination.total"
    ></el-pagination>
    </el-row>
  </div>
</template>

<script>
  import SearchBar from './SearchBar'
  import ViewSwitch from './ViewSwitch'

  export default {
    name: 'Books',
    components: {SearchBar, ViewSwitch},
    data () {
      return {
        books: [{
    id: 1,
    title: '书籍标题1',
    author: '作者A',
    date: '2024-01-01',
    press: '出版社X',
    abs: '书籍1的描述',
    cover: '@/assets/1.jpg'
  },
  {
    id: 2,
    title: '书籍标题2',
    author: '作者B',
    date: '2024-02-01',
    press: '出版社Y',
    abs: '书籍2的描述',
    cover: 'https://via.placeholder.com'
  },],
  pagination: {//分页相关模型数据
                currentPage: 1,//当前页码
                pageSize:4,//每页显示的记录数
                total:5,//总记录数
            }
      }
    },
    mounted: function () {
       this.loadBooks()
      
    },
    methods: {
      loadBooks () {
        // var _this = this
        // this.$axios.get('/books').then(resp => {
        //   if (resp && resp.data.code === 200) {
        //     _this.books = resp.data.result
        //   }
        // })
        this.pagination.total=this.books.length

      },
      handleCurrentChange: function (currentPage) {
        this.pagination.currentPage =  currentPage
      },
      searchResult () {
        var _this = this
        this.$axios
          .get('/search?keywords=' + this.$refs.searchBar.keywords, {
          }).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.books = resp.data.result
          }
        })
      }
    }
  }
</script>
<style scoped>

  .cover {
    width: 115px;
    height: 172px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
  }

  img {
    width: 115px;
    height: 172px;
 
  }

  .title {
    font-size: 14px;
    text-align: left;
  }

  .author {
    color: #333;
    width: 102px;
    font-size: 13px;
    margin-bottom: 6px;
    text-align: left;
  }

  .abstract {
    display: block;
    line-height: 17px;
  }

  .el-icon-delete {
    cursor: pointer;
    float: right;
  }

  .switch {
    display: flex;
    position: absolute;
    left: 780px;
    top: 25px;
  }

  a {
    text-decoration: none;
  }

  a:link, a:visited, a:focus {
    color: #3377aa;
  }
  .el-pagination {
  margin-top: 30px; 
  text-align: center; 
}
</style> -->

<template>
  <div>
    <el-row style="height: 600px;">
      <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>
      <view-switch class="switch"></view-switch>
      <el-tooltip effect="dark" placement="right"
                  v-for="item in books.slice((pagination.currentPage-1)*pagination.pageSize,
                  pagination.currentPage*pagination.pageSize)"
                  :key="item.id">
        <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.title}}</p>
        <p slot="content" style="font-size: 13px;margin-bottom: 6px">
          <span>{{item.author}}</span> 
          <!-- <span>{{item.date}}</span> /
          <span>{{item.press}}</span> -->
        </p>
        <p slot="content" style="width: 300px" class="abstract">{{item.abs}}</p>
        <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                 bodyStyle="padding:10px" shadow="hover">
          <div class="cover" @click="goToBookInfo(item.id)">
            <img :src="item.cover" alt="封面">
          </div>
          <div class="info">
            <div class="title">
              <a href="">{{item.title}}</a>
            </div>
          </div>
          <div class="author">{{item.author}}</div>
        </el-card>
      </el-tooltip>
    </el-row>

    <el-row>
      <el-pagination
      background
      layout="total, prev, pager, next, jumper"
      @current-change="handleCurrentChange"
      :page-size="pagination.pageSize"
      :current-page="pagination.currentPage"
      :total="pagination.total"
    ></el-pagination>
    </el-row>
  </div>
</template>

<script>
import SearchBar from './SearchBar'
import ViewSwitch from './ViewSwitch'

export default {
  name: 'Books',
  components: {SearchBar, ViewSwitch},
  data () {
    return {
      books: [
        {
          id: 1,
          title: '书籍标题1',
          author: '作者A',
          date: '2024-01-01',
          press: '出版社X',
          abs: '书籍1的描述',
          cover: '@/assets/1.jpg'
        },
        {
          id: 2,
          title: '书籍标题2',
          author: '作者B',
          date: '2024-02-01',
          press: '出版社Y',
          abs: '书籍2的描述',
          cover: 'https://via.placeholder.com'
        }
      ],
      pagination: {
        currentPage: 1,
        pageSize: 4,
        total: 5
      }
    }
  },
  mounted: function () {
    this.loadBooks()
  },
  methods: {
    loadBooks () {
      // var _this = this
      // this.$axios.get('/books').then(resp => {
      //   if (resp && resp.data.code === 200) {
      //     _this.books = resp.data.result
      //   }
      // })
      this.pagination.total = this.books.length
    },
    handleCurrentChange: function (currentPage) {
      this.pagination.currentPage = currentPage
    },
    searchResult () {
      var _this = this
      this.$axios
        .get('/search?keywords=' + this.$refs.searchBar.keywords, {
        }).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.books = resp.data.result
        }
      })
    },
    goToBookInfo (id) {
      this.$router.push({ name: 'BookInfo', params: { id: id } })
    }
  }
}
</script>
<style scoped>
  .cover {
    width: 115px;
    height: 172px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
  }

  img {
    width: 115px;
    height: 172px;
  }

  .title {
    font-size: 14px;
    text-align: left;
  }

  .author {
    color: #333;
    width: 102px;
    font-size: 13px;
    margin-bottom: 6px;
    text-align: left;
  }

  .abstract {
    display: block;
    line-height: 17px;
  }

  .el-icon-delete {
    cursor: pointer;
    float: right;
  }

  .switch {
    display: flex;
    position: absolute;
    left: 780px;
    top: 25px;
  }

  a {
    text-decoration: none;
  }

  a:link, a:visited, a:focus {
    color: #3377aa;
  }
  .el-pagination {
    margin-top: 30px;
    text-align: center;
  }
</style>

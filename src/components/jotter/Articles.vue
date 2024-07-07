<!-- <template>
  <div style="margin-top: 40px">

    <div class="articles-area">
      <el-card style="text-align: left">
        <div v-for="article in articles" :key="article.id">
          <div style="float:left;width:85%;height: 150px;">
            <router-link class="article-link" :to="{path:'jotter/article',query:{id: article.id}}"><span style="font-size: 20px"><strong>{{article.articleTitle}}</strong></span></router-link>
            <el-divider content-position="left">{{article.articleDate}}</el-divider>
            <router-link class="article-link" :to="{path:'jotter/article',query:{id: article.id}}"><p>{{article.articleAbstract}}</p></router-link>
          </div>
          <el-image
            style="margin:18px 0 0 30px;width:100px;height: 100px"
            :src="article.articleCover"
            fit="cover"></el-image>
          <el-divider></el-divider>
        </div>
      </el-card>
    </div>
    <el-pagination
      background
      layout="total, prev, pager, next, jumper"
      @current-change="handleCurrentChange"
      :page-size="pageSize"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>

  export default {
    name: 'Articles',
    data () {
      return {
        articles: [],
        pageSize: 4,
        total: 0
      }
    },
    mounted () {
      //this.loadArticles()
    },
    methods: {
      // loadArticles () {
      //   var _this = this
      //   this.$axios.get('/article/' + this.pageSize + '/1').then(resp => {
      //     if (resp && resp.data.code === 200) {
      //       _this.articles = resp.data.result.content
      //       _this.total = resp.data.result.totalElements
      //     }
      //   })
      // },
      // handleCurrentChange (page) {
      //   var _this = this
      //   this.$axios.get('/article/' + this.pageSize + '/' + page).then(resp => {
      //     if (resp && resp.data.code === 200) {
      //       _this.articles = resp.data.result.content
      //       _this.total = resp.data.result.totalElements
      //     }
      //   })
      // }
    }
  }
</script>

<style scoped>
  .articles-area {
    width: 990px;
    height: 750px;
    margin-left: auto;
    margin-right: auto;
  }

  .article-link {
    text-decoration: none;
    color: #606266;
  }

  .article-link:hover {
    color: #409EFF;
  }
</style> -->

<!-- <template>
  <div>111</div>
</template> -->

<template>
  <div style="margin-top: 40px; overflow-y: scroll;max-height: 620px;">
    <div class="articles-area">
      <el-card style="text-align: left">
        <div v-for="article in articles" :key="article.id">
          <div style="float:left;width:85%;height: 150px;">
            <router-link class="article-link" :to="{ path: 'jotter/article', query: { id: article.id } }">
              <span style="font-size: 20px"><strong>{{ article.articleTitle }}</strong></span>
            </router-link>
            <el-divider content-position="left">{{ article.articleDate }}</el-divider>
            <router-link class="article-link" :to="{ path: 'jotter/article', query: { id: article.id } }">
              <p>{{ article.articleAbstract }}</p>
            </router-link>
          </div>
          <el-image
            style="margin:18px 0 0 30px;width:100px;height: 100px"
            :src="article.articleCover"
            fit="cover"
          ></el-image>
          <el-divider></el-divider>
        </div>
      </el-card>
    </div>
    <el-pagination
      background
      layout="total, prev, pager, next, jumper"
      @current-change="handleCurrentChange"
      :page-size="pagination.pageSize"
      :current-page="pagination.currentPage"
      :total="pagination.total"
    ></el-pagination>
  </div>
</template>

<script>
export default {
  name: 'Articles',
  data() {
    return {
      articles: [],
      pagination: {//分页相关模型数据
                currentPage: 1,//当前页码
                pageSize:4,//每页显示的记录数
                total:5,//总记录数
            }
    };
  },
  mounted() {
    this.loadArticles(1); // 初次加载时获取第一页的文章
  },
  methods: {
    loadArticles(page) {
      // 模拟的测试数据
      const testData = [
        {
          id: 1,
          articleTitle: '测试文章1',
          articleDate: '2024-07-01',
          articleAbstract: '这是测试文章1的摘要。',
          articleCover: 'https://via.placeholder.com/100'
        },
        {
          id: 2,
          articleTitle: '测试文章2',
          articleDate: '2024-07-02',
          articleAbstract: '这是测试文章2的摘要。',
          articleCover: 'https://via.placeholder.com/100'
        },
        {
          id: 3,
          articleTitle: '测试文章3',
          articleDate: '2024-07-03',
          articleAbstract: '这是测试文章3的摘要。',
          articleCover: 'https://via.placeholder.com/100'
        },
        {
          id: 4,
          articleTitle: '测试文章4',
          articleDate: '2024-07-04',
          articleAbstract: '这是测试文章4的摘要。',
          articleCover: 'https://via.placeholder.com/100'
        },
        {
          id: 4,
          articleTitle: '5',
          articleDate: '2024-07-04',
          articleAbstract: '这是测试文章4的摘要。',
          articleCover: 'https://via.placeholder.com/100'
        },
      ];

      // 模拟分页逻辑
      this.articles = testData.slice((page - 1) * this.pagination.pageSize, page * this.pagination.pageSize);
      this.total = testData.length;
    },
    handleCurrentChange(page) {
      this.pagination.currentPage = page; // 更新当前页码
      this.loadArticles(page);
    }
  }
};
</script>

<style scoped>
.articles-area {
  width: 990px;
  margin: 0 auto;

}

.article-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  border-bottom: 1px solid #ddd;
}

.article-link {
  text-decoration: none;
  color: #606266;
}

.article-link:hover {
  color: #409EFF;
}

.el-image {
  flex-shrink: 0;
}
.el-pagination {
  margin-top: 30px; /* 可选：设置分页组件与文章列表的间距 */
  text-align: center; /* 将分页组件内部内容居中 */
}
</style>
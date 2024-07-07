<!-- <template>
  <div class="articles-area">
    <el-card style="text-align: left;width: 990px;margin: 35px auto 0 auto">
      <div>
        <span style="font-size: 20px"><strong>{{article.articleTitle}}</strong></span>
        <el-divider content-position="left">{{article.articleDate}}</el-divider>
        <div class="markdown-body">
          <div v-html="article.articleContentHtml"></div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: 'ArticleDetails',
    data () {
      return {
        article: []
      }
    },
    mounted () {
      this.loadArticle()
    },
    methods: {
      loadArticle () {
        var _this = this
        this.$axios.get('/article/' + this.$route.query.id).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.article = resp.data.result
          }
        })
      }
    }
  }
</script>

<style scoped>
  @import "../../styles/markdown.css";
</style> -->

<template>
  <div class="articles-area">
    <el-card style="text-align: left; width: 990px; margin: 35px auto 0 auto;">
      <div>
        <span style="font-size: 20px"><strong>{{ article.articleTitle }}</strong></span>
        <el-divider content-position="left">{{ article.articleDate }}</el-divider>
        <div class="markdown-body">
          <div v-html="article.articleContentHtml"></div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'ArticleDetails',
  data() {
    return {
      article: {
        articleTitle: '测试文章标题',
        articleDate: '2024-07-01',
        articleContentHtml: '<p>这是测试文章内容。</p><p>支持 <strong>HTML</strong> 格式。</p>'
      }
    }
  }
}
</script>

<style scoped>
@import "../../styles/markdown.css";
</style>

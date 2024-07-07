<!-- <template>
  <div class="container">
    <div class="book-info">
      <el-row :gutter="20">
        <el-col :span="6">
          <img src="@/assets/2.jpg" alt="Book Cover" class="book-cover">
        </el-col>
        <el-col :span="18" class="book-details">
          <h3 style="margin-bottom: 8px;">{{ book.title }}</h3>
          <p style="margin: 5px">{{ book.author }}</p>
          <p style="margin: 5px;">{{ book.date }}</p>
          <p class="description">{{ book.abs }}</p>
          <div class="actions">
            <el-button type="primary">立即阅读</el-button>
            <el-button>加入书架</el-button>
          </div>
        </el-col>
      </el-row>
      <div class="m-bookauthor">
        <h4>作者简介</h4>
        <div class="f-border detail">
          <div class="txt">
            <p>
              鲁迅（1881年9月25日—1936年10月19日），原名周樟寿，1898年改为周树人，字豫山、
              豫亭，后改名为豫才。以笔名鲁迅闻名于世，浙江绍兴人，为中国的近代著名作家，新文化运
              动的领导人之一，
              中国现代文学的奠基人和开山巨匠，在西方世界享有盛誉的中国近代文学家、思想家。
            </p>
            <p>
              鲁迅的主要成就包括杂文、短中篇小说、文学、思想和社会评论、学术著作、自然科学著作、
              古代典籍校勘与研究、散文、现代散文诗、旧体诗、外国文学与学术翻译作品和木刻版画的研究，对于五
              四运动以后的中国社会思想文化发展产生一定的影响，蜚声世界文坛，尤其在韩国、日本思想文化领域有极其重要
              的地位和影响，被誉为“二十世纪东亚文化地图上占最大领土的作家”。毛泽东评价他
              是伟大的无产阶级的文学家、思想家、革命家，是中国文化革命的主将，也被人民称为“民族魂”。
            </p>
            <p>
              鲁迅的主要成就包括杂文、短中篇小说、文学、思想和社会评论、学术著作、自然科学著作、
              古代典籍校勘与研究、散文、现代散文诗、旧体诗、外国文学与学术翻译作品和木刻版画的研究，对于五
              四运动以后的中国社会思想文化发展产生一定的影响，蜚声世界文坛，尤其在韩国、日本思想文化领域有极其重要
              的地位和影响，被誉为“二十世纪东亚文化地图上占最大领土的作家”。毛泽东评价他
              是伟大的无产阶级的文学家、思想家、革命家，是中国文化革命的主将，也被人民称为“民族魂”。
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BookInfo',
  props: ['id'],
  data() {
    return {
      book: {}
    };
  },
  mounted() {
    this.loadBookInfo();
  },
  methods: {
    loadBookInfo() {
      // 模拟加载图书信息
      const books = [
        {
          id: 1,
          title: '狂人日记',
          author: '鲁迅',
          date: '2024-01-01',
          press: '出版社X',
          abs: '小说通过被迫害者“狂人”的形象以及“狂人”的自述式的描写，揭示了封建礼教的“吃人”本质，表现了作者对以封建礼教为主体内涵的中国封建文化的反抗；也表现了作者深刻的忏悔意识。作者以彻底的“革命民主主义”的立场对中国的文化进行了深刻的反思，同时对中国的甚至是人类的前途表达了深广的忧愤。',
          cover: '@/assets/1.jpg'
        }
      ];
      this.book = books.find(book => book.id == this.id) || {};
    }
  }
};
</script>

<style scoped>
.container {

}

.book-info {
  margin-top: 50px;
  margin-left: 50px;
  width: 80%;
  
}

.book-cover {
  width: 300px;
  height: 350px;
  margin-right: 20px;
}

.book-details {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.book-details h3 {
  font-size: 20px;
  font-weight: 700;
  margin: 0;
  padding-top: 25px;
}

.description {
  width: 70%;
  color: #545c66;
  font-size: 15px;
  margin-top: 1px;
  height: 130px;
}

.actions {
  display: flex;
  gap: 10px;
  margin-top: auto;
}

.m-bookauthor h4 {
  font-size: 16px;
  font-weight: 700;
  color: #06080a;
  padding-bottom: 10px;
}

.m-bookauthor .detail {
  padding: 30px 20px;
  color: #888;
  line-height: 20px;
  border: 1px solid #ebe6e1;
}

.m-bookauthor {
  margin-top: 30px;
}

.m-bookauthor h4 {
  font-size: 16px;
  font-weight: 700;
  color: #06080a;
  padding-bottom: 10px;
}

.m-bookauthor .detail {
  padding: 15px 20px;
  color: #888;
  line-height: 20px;
  border: 1px solid #ebe6e1;
}

.m-bookauthor .detail a {
  color: #655;
  font-size: 16px;
}

.m-bookauthor .detail a:hover {
  color: #ca483b;
}

.m-bookauthor .detail span {
  float: right;
  color: #888;
}

.m-bookauthor h5 {
  font-size: 14px;
  color: #655;
  margin-bottom: 25px;
}

.m-bookauthor .txt a {
  display: inline-block;
  margin-left: 10px;
  text-indent: -9999px;
  width: 40px;
  height: 20px;
}

.txt p {
  font-size: 14px;
  color: #655;
}
</style> -->

<template>
  <div class="outer-container">
    <div class="container">
      <div class="book-info">
        <el-row :gutter="20">
          <el-col :span="6">
            <img src="@/assets/2.jpg" alt="Book Cover" class="book-cover">
          </el-col>
          <el-col :span="18" class="book-details">
            <h3 style="margin-bottom: 8px;">{{ book.title }}</h3>
            <p style="margin: 5px">{{ book.author }}</p>
            <p style="margin: 5px;">{{ book.date }}</p>
            <p class="description">{{ book.abs }}</p>
            <div class="actions">
              <el-button type="primary">立即阅读</el-button>
              <el-button>加入书架</el-button>
            </div>
          </el-col>
        </el-row>
        <div class="m-bookauthor">
          <h4>作者简介</h4>
          <div class="f-border detail">
            <div class="txt">
              <p>
                鲁迅（1881年9月25日—1936年10月19日），原名周樟寿，1898年改为周树人，字豫山、
                豫亭，后改名为豫才。以笔名鲁迅闻名于世，浙江绍兴人，为中国的近代著名作家，新文化运
                动的领导人之一，
                中国现代文学的奠基人和开山巨匠，在西方世界享有盛誉的中国近代文学家、思想家。
              </p>
              <p>
                鲁迅的主要成就包括杂文、短中篇小说、文学、思想和社会评论、学术著作、自然科学著作、
                古代典籍校勘与研究、散文、现代散文诗、旧体诗、外国文学与学术翻译作品和木刻版画的研究，对于五
                四运动以后的中国社会思想文化发展产生一定的影响，蜚声世界文坛，尤其在韩国、日本思想文化领域有极其重要
                的地位和影响，被誉为“二十世纪东亚文化地图上占最大领土的作家”。毛泽东评价他
                是伟大的无产阶级的文学家、思想家、革命家，是中国文化革命的主将，也被人民称为“民族魂”。
              </p>
              <p>
                鲁迅的主要成就包括杂文、短中篇小说、文学、思想和社会评论、学术著作、自然科学著作、
                古代典籍校勘与研究、散文、现代散文诗、旧体诗、外国文学与学术翻译作品和木刻版画的研究，对于五
                四运动以后的中国社会思想文化发展产生一定的影响，蜚声世界文坛，尤其在韩国、日本思想文化领域有极其重要
                的地位和影响，被誉为“二十世纪东亚文化地图上占最大领土的作家”。毛泽东评价他
                是伟大的无产阶级的文学家、思想家、革命家，是中国文化革命的主将，也被人民称为“民族魂”。
              </p>
            </div>
          </div>
        </div>
        <div style="margin-top: 20px;">
          <comments></comments>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import comments from './Comments.vue'
export default {
  name: 'BookInfo',
  components: {comments},
  props: ['id'],
  data() {
    return {
      book: {}
    };
  },
  mounted() {
    this.loadBookInfo();
  },
  methods: {
    loadBookInfo() {
      // 模拟加载图书信息
      const books = [
        {
          id: 1,
          title: '狂人日记',
          author: '鲁迅',
          date: '2024-01-01',
          press: '出版社X',
          abs: '小说通过被迫害者“狂人”的形象以及“狂人”的自述式的描写，揭示了封建礼教的“吃人”本质，表现了作者对以封建礼教为主体内涵的中国封建文化的反抗；也表现了作者深刻的忏悔意识。作者以彻底的“革命民主主义”的立场对中国的文化进行了深刻的反思，同时对中国的甚至是人类的前途表达了深广的忧愤。',
          cover: '@/assets/1.jpg'
        }
      ];
      this.book = books.find(book => book.id == this.id) || {};
    }
  }
};
</script>

<style scoped>
.outer-container {
  height:90vh;
  overflow-y: auto;
}

.container {
  padding: 20px;
}

.book-info {
  margin-top: 50px;
  margin-left: 50px;
  width: 80%;
}

.book-cover {
  width: 300px;
  height: 350px;
  margin-right: 20px;
}

.book-details {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.book-details h3 {
  font-size: 20px;
  font-weight: 700;
  margin: 0;
  padding-top: 25px;
}

.description {
  width: 70%;
  color: #545c66;
  font-size: 15px;
  margin-top: 1px;
  height: 130px;
}

.actions {
  display: flex;
  gap: 10px;
  margin-top: auto;
}

.m-bookauthor h4 {
  font-size: 16px;
  font-weight: 700;
  color: #06080a;
  padding-bottom: 10px;
}

.m-bookauthor .detail {
  padding: 30px 20px;
  color: #888;
  line-height: 20px;
  border: 1px solid #ebe6e1;
}

.m-bookauthor {
  margin-top: 30px;
}

.m-bookauthor h4 {
  font-size: 16px;
  font-weight: 700;
  color: #06080a;
  padding-bottom: 10px;
}

.m-bookauthor .detail {
  padding: 15px 20px;
  color: #888;
  line-height: 20px;
  border: 1px solid #ebe6e1;
}

.m-bookauthor .detail a {
  color: #655;
  font-size: 16px;
}

.m-bookauthor .detail a:hover {
  color: #ca483b;
}

.m-bookauthor .detail span {
  float: right;
  color: #888;
}

.m-bookauthor h5 {
  font-size: 14px;
  color: #655;
  margin-bottom: 25px;
}

.m-bookauthor .txt a {
  display: inline-block;
  margin-left: 10px;
  text-indent: -9999px;
  width: 40px;
  height: 20px;
}

.txt p {
  font-size: 14px;
  color: #655;
}
</style>

import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';                      // 引入element-ui
import 'element-ui/lib/theme-chalk/index.css';           // element-ui的css样式要单独引入

import 'vue-area-linkage/dist/index.css'; // v2 or higher
import VueAreaLinkage from 'vue-area-linkage';
Vue.use(VueAreaLinkage);



Vue.config.productionTip = false
Vue.use(ElementUI);   // 这种方式引入了ElementUI中所有的组件


// import axios from './utils/axios';
// Vue.prototype.$axios=axios

import axios from 'axios'
Vue.prototype.$axios = axios


new Vue({
  router,
  store,
  axios,
  render: h => h(App)
}).$mount('#app')

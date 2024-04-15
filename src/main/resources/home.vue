<template>
  <div class="home">
    <button @click="getReport">Download Report</button>
  </div>
</template>

<script>
import axios from 'axios'
import download from 'downloadjs'
export default {
  name: 'Home',
  methods: {
    getReport () {
      axios.get('/du/d1/demo-report', { responseType: 'arraybuffer' }).then(res => {
        download(res.data, `${(Math.random() * 100000).toFixed(0)}.pdf`, res.headers['content-type'])
      })
    }
  }
}
</script>

<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="jobNameSearch" placeholder="项目名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button  @click="search()">查询</el-button>
        <!--v-if="isAuth('operation:tag:save')"-->
        <el-button  type="primary" @click="create()">新增</el-button>
        <!--v-if="isAuth('operation:tag:delete')"-->
        <el-button  type="danger" @click="removes()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="jobId"
        header-align="center"
        align="center"
        label="项目编号"
        width="80">
      </el-table-column>
      <el-table-column
        prop="jobName"
        header-align="center"
        align="center"
        label="项目名称">
      </el-table-column>
      <el-table-column
        prop="jobDescription"
        header-align="center"
        align="center"
        label="项目描述">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间">
        <template slot-scope="scope">
          {{ scope.row.updateTime | dateformat }}
        </template>
      </el-table-column>
      <el-table-column
        prop="updateTime"
        header-align="center"
        align="center"
        label="更新时间">
        <template slot-scope="scope">
          {{ scope.row.updateTime | dateformat }}
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <!--@click="addOrUpdateHandle(scope.row.id)"-->
          <el-button type="text" size="small" @click="update(scope.row)">修改</el-button>
          <el-button type="text" size="small" @click="remove(scope.row.jobId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[7, 10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'job_list',
  data () {
    return {
      jobNameSearch: '',
      dataForm: {
        jobName:''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 7,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  created(){
    this.getDataList();
  },
  methods:{
    async getDataList(){
      let queryCondition = {
        jobName: ''
      };
      let params = {
        currPage : this.pageIndex,
        pageSize : this.pageSize,
        dataForm: queryCondition
      };
      let response = await this.$api.job.listByPage(params);
      console.log(response);
      this.totalPage = response.data.total;
      this.dataList = response.data.record;
    },
    //搜索
    async search(){
      this.pageIndex = 1;
      let queryCondition = {
        jobName: this.jobNameSearch
      };
      let params = {
        currPage : this.pageIndex,
        pageSize : this.pageSize,
        dataForm: queryCondition
      };
      let response = await this.$api.job.listByPage(params);
      console.log(response);
      this.totalPage = response.data.total;
      this.dataList = response.data.record;
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList()
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    //删除操作
    remove(jobId){
      let params = {
        jobId: jobId
      };
      this.$confirm('将删除该内容, 是否确定?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return this.$api.job.remove(params);
      }).then((result) => {
        console.log(result);
        if (result.data.code === 200) {
          this.$message.success("删除成功");
          this.getDataList();
        }
      }).catch((result) => {
        if (result.data.code === 200) {
          this.$message.error("删除失败")
        }
      });
    },
    //多选删除
    removes() {
      let ids = this.dataListSelections.map(item => {
        return item.jobId
      });
      //删除数组
      let params = ids;
      console.log(params);
      this.$confirm('将删除选中内容, 是否确定?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        return this.$api.job.removes(params);
      }).then((result)=>{
        if(result.data.code === 200){
          this.$message.success("删除成功");
          this.getDataList();
        }
      }).catch((result)=>{
        if(result.data.code === 200){
          this.$message.error("删除失败")
        }
      });
    },
    //创建标签
    create(){
      this.$router.push('/hour/job/create');
    },
    //修改博客
    update(row){
      this.$router.push({
        path: '/hour/job/create',
        query: {
          jobId: row.jobId,
          jobName: row.jobName,
          jobDescription: row.jobDescription
        }
      })
    }
  }
}
</script>

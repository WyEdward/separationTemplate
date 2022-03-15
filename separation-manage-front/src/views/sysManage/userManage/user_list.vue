<template>
  <div class="mod-config">
    <el-form :inline="true">
      <el-form-item>
        <el-autocomplete
          value-key="userName"
          v-model="userNameSearch"
          :fetch-suggestions="querySearchGroup"
          placeholder="请输入用户名称"
          @focus="handlerInputChange"
        >
          <i
            class="el-icon-edit el-input__icon"
            slot="suffix"
          >
          </i>
        </el-autocomplete>
      </el-form-item>
      <el-form-item>
        <el-button  @click="querySearch(1)">查询</el-button>
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
        prop="userId"
        header-align="center"
        align="center"
        label="用户编号"
        width="80">
      </el-table-column>
      <el-table-column
        prop="userName"
        header-align="center"
        align="center"
        label="用户名称">
      </el-table-column>
      <el-table-column
        prop="userNick"
        header-align="center"
        align="center"
        label="用户昵称">
      </el-table-column>
      <el-table-column
        prop="roleList"
        header-align="center"
        align="center"
        label="角色"
        width="300">
        <template slot-scope="scope">
          <el-row>
            <el-button v-for="role in scope.row.roleList" :key="role.roleId" size="mini">{{role.roleName}}</el-button>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column
        prop="userEmail"
        header-align="center"
        align="center"
        label="用户邮箱">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间">
        <template slot-scope="scope">
          {{ scope.row.createTime | dateformat }}
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
          <el-button type="text" size="small" @click="remove(scope.row.userId)">删除</el-button>
          <el-button type="text" size="small" @click="resetPassword(scope.row.userName)">重置密码</el-button>
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
  name: 'user_list',
  data () {
    return {
      groupArr: [],   //根据输入信息模糊的下拉框内容
      groupList: [],  //后台返回的直接查的数据
      userNameSearch: '',
      dataForm: {
        userName:''
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
    //根据查询词去得到下拉菜单  cb(groupArr) 表示下拉菜单应用这个数组
    querySearchGroup(queryString, cb) {
      this.queryGroupByLike(queryString);
      cb(this.groupArr);   //建议菜单就是groupArr数组
    },
    //根据查询词去后台模糊查询前10条数据(核心方法)
    async queryGroupByLike(queryString){
      let queryCondition = {
        queryString : queryString
      }
      let res = await this.$api.user.queryGroupByLike(queryCondition);
      this.groupList = res.data.record;
      if(this.groupList.length >= 1){
        this.groupArr = this.groupList.map((item)=>{
          return {
            userName: item.userName,
          }
        })
      }else{
        this.groupArr = [{userName: '关键词下暂无数据'}];   //搜不到就显示为空
      }
    },
    handlerInputChange(){
      this.queryGroupByLike(this.userNameSearch);
    },
    async getDataList(){
      let queryCondition = {
        userName: ''
      };
      let params = {
        currPage : this.pageIndex,
        pageSize : this.pageSize,
        dataForm: queryCondition
      };
      let response = await this.$api.user.listDtoByPage(params);
      console.log(response);
      this.totalPage = response.data.total;
      this.dataList = response.data.record;
    },
    //搜索 加第几页参数
    async search(){
      let queryCondition = {
        userName: this.userNameSearch
      };
      let params = {
        currPage : this.pageIndex,
        pageSize : this.pageSize,
        dataForm: queryCondition
      };
      let response = await this.$api.user.listDtoByPage(params);
      //console.log(response);
      this.totalPage = response.data.total;
      this.dataList = response.data.record;
    },
    //点击查询默认跳到第一页
    querySearch(pageIndex){
      this.pageIndex = pageIndex;
      this.search()
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.search()
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.search()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    //删除操作
    remove(userId){
      let params = {
        userId: userId
      };
      this.$confirm('将删除该内容, 是否确定?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return this.$api.user.remove(params);
      }).then((result) => {
        //console.log(result);
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
        return item.userId
      });
      //删除数组
      let params = ids;
      console.log(params);
      this.$confirm('将删除选中内容, 是否确定?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        return this.$api.user.removes(params);
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
      this.$router.push('/sys/user/create');
    },
    //修改博客
    update(row){
      this.$router.push({
        path: '/sys/user/create',
        query: {
          userId: row.userId,
          userName: row.userName,
          userNick: row.userNick,
          userEmail: row.userEmail,
          userDepartment: row.userDepartment,
          roleList: row.roleList.map((item)=> {
            return item.roleId
          }),
        }
      })
    },
    async resetPassword(userName){
      let params ={
        userName : userName
      }
      let res = await this.$api.user.resetDefaultPassword(params);
      if(res.data.code === 200){
        this.$message.success("重置默认密码成功");
      }else{
        this.$message.error("重置默认密码失败");
      }
    }
  },
  watch:{
    'userNameSearch': {
      deep: true,
      handler: function(newVal, oldVal) {
        this.handlerInputChange();
      }
    }
  }
}
</script>

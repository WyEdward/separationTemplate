<template>
    <div class="MainContent">
        <el-tabs
                :value="activeIndex"
                type="border-card"
                closable
                @tab-click="tabClick"
                v-if="options.length"
                @tab-remove="tabRemove">
            <el-tab-pane :key="item.route"
                         v-for="(item) in options"
                         :label="item.name"
                         :name="item.route">
            </el-tab-pane>
            <transition name="fade" mode="out-in">
                <keep-alive>
                    <router-view  v-if="$route.meta.keepAlive">
                        <!-- 这里是会被缓存的视图组件 -->
                    </router-view>
                </keep-alive>
            </transition>
            <transition name="fade" mode="out-in">
                <router-view v-if="!$route.meta.keepAlive">
                    <!-- 这里是不被缓存的视图组件 -->
                </router-view>
            </transition>
        </el-tabs>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        name: "MainContent",
        computed:{
            ...mapState('menu', ['options', 'activeIndex'])
        },
        methods: {
            // tab切换时，动态的切换路由
            tabClick(tab) {
                let path = tab.name;
                this.$store.commit('menu/set_active_index', path);
                this.$router.replace({path: path});
            },
            tabRemove(targetName) {
                // 首页不可删除
                if (targetName === '/home' || targetName === '') {
                    return;
                }
                this.$store.commit('menu/delete_tabs', targetName);
                if (this.activeIndex === targetName) {
                    //console.log(this.activeIndex + '-' + targetName);
                    // 设置当前激活的路由
                    if (this.options && this.options.length >= 1) {
                        this.$store.commit('menu/set_active_index', this.options[this.options.length - 1].route);
                        this.$router.replace({path: this.activeIndex});
                    } else {
                        this.$router.replace({path: '/'});
                    }
                }
            },
            // 刷新前缓存tab
            beforeUnload() {
                // 监听页面刷新
                window.addEventListener("beforeunload", () => {
                    // visitedViews数据结构太复杂无法直接JSON.stringify处理，先转换需要的数据
                    let tabViews = this.options.map(item => {
                        return {
                            name: item.name,
                            route: item.route
                        };
                    });
                    sessionStorage.setItem("tabViews", JSON.stringify(tabViews));
                    sessionStorage.setItem("activeIndex", this.activeIndex);
                });
                // 页面初始化加载判断缓存中是否有数据
                let oldViews = JSON.parse(sessionStorage.getItem("tabViews")) || [];
                if (oldViews.length > 0) {
                    this.$store.state.menu.options = oldViews;
                }

                this.$store.state.menu.activeIndex = sessionStorage.getItem("activeIndex");
            }
        },
        mounted() {
            // 页面刷新前缓存和赋值
            this.beforeUnload();
        },
        watch: {
            '$route'(to) {
                let flag = false;
                for (let option of this.options ) {
                    if (option.route === to.path) {
                        flag = true;
                        this.$store.commit('menu/set_active_index', to.path);
                        break
                    }
                }
                if (!flag) {
                    this.$store.commit('menu/add_tabs', {route: to.path, name: to.name});
                    this.$store.commit('menu/set_active_index', to.path);
                }
            }
        }
    }
</script>

<style scoped>

</style>

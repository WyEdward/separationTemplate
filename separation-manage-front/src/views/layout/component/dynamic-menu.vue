<template>
    <div class="dynamicMenu">
        <template v-for='v in menuList'>
            <el-submenu
                    :index='v.path'
                    v-if='v.children&&v.children.length>0'
                    :key='v.path'
            >
                <template slot='title'>
                    <i class="el-icon-location"></i>
                   <!-- <svg-icon v-if="v.meta&&v.meta.icon" :icon-class="v.meta.icon"></svg-icon>-->
                    <span>{{v.meta.name}}</span>
                </template>
                <el-menu-item-group>
                    <my-nav :menuList='v.children'></my-nav>
                </el-menu-item-group>
            </el-submenu>

            <el-menu-item
                    :key='v.path'
                    :index='v.path'
                    v-else
            >
                <i class="el-icon-location"></i>
                <!--<svg-icon v-if="v.meta&&v.meta.icon" :icon-class="v.meta.icon"></svg-icon>-->
                <span slot='title'>{{v.meta.name}}</span>
            </el-menu-item>
        </template>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        name: "my-nav",
        data() {
            return {

            };
        },
        props: {
            menuList: {
                type: Array,
                default: () => {
                    return []
                }
            }
        },
        computed:{
            ...mapState(['isSidebarNavCollapse'])
        },
        methods: {

        }
    }
</script>

<style lang="scss" scoped>
    /*折叠菜单下的样式*/
    .navCollapsed {
        .dynamicMenu {
            span {
                display: none;
            }
        }
    }
</style>

import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import UserMng from '../components/UserMng.vue'
import UnitMng from '../components/UnitMng.vue'
import PostSet from '../components/PostSet.vue'
import UserPostMng from '../components/UserPostMng.vue'
import UserPostInfo from '../components/UserPostInfo.vue'
import SalarySet from '../components/SalarySet.vue'
import UserSalaryMng from '../components/UserSalaryMng.vue'
import UserSalaryInfo from '../components/UserSalaryInfo.vue'
import UserCertMng from '../components/UserCertMng.vue'
import AllUserCertMng from '../components/AllUserCertMng.vue'
import AppliedCertFlow from '../components/AppliedCertFlow.vue'
import PendingCertFlow from '../components/PendingCertFlow.vue'
import UserHolidayInfo from '../components/UserHolidayInfo.vue'
import UserHolidayFlowInfo from '../components/UserHolidayFlowInfo.vue'
import AllUserHolidayInfo from '../components/AllUserHolidayInfo.vue'
import HolidayApply from '../components/HolidayApply.vue'
import PendingHolidayFlow from '../components/PendingHolidayFlow.vue'
import UserInfo from '../components/UserInfo.vue'
import ChangePassword from '../components/ChangePassword.vue'
Vue.use(VueRouter)
const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      { path: '/user_mng', component: UserMng },
      { path: '/unit_mng', component: UnitMng },
      { path: '/post_set', component: PostSet },
      { path: '/user_post_mng', component: UserPostMng },
      { path: '/user_post_info', component: UserPostInfo },
      { path: '/salary_set', component: SalarySet },
      { path: '/user_salary_mng', component: UserSalaryMng },
      { path: '/user_salary_info', component: UserSalaryInfo },
      { path: '/user_cert_mng', component: UserCertMng },
      { path: '/alluser_cert_mng', component: AllUserCertMng },
      { path: '/applied_cert_flow', component: AppliedCertFlow },
      { path: '/pending_cert_flow', component: PendingCertFlow },
      { path: '/user_hoiday_info', component: UserHolidayInfo },
      { path: '/user_holiday_flow_info', component: UserHolidayFlowInfo },
      { path: '/alluser_holiday_info', component: AllUserHolidayInfo },
      { path: '/holiday_apply', component: HolidayApply },
      { path: '/pending_holiday_flow', component: PendingHolidayFlow },
      { path: '/user_info', component: UserInfo },
      { path: '/change_password', component: ChangePassword }
    ]
  }
]

const router = new VueRouter({
  routes
})

// 路由导航守卫
router.beforeEach((to, from, next) => {
  // 如果访问的是登录页，直接放行
  if (to.path === '/login') return next()
  // 取token
  const token = window.sessionStorage.getItem('token')
  // 如果不存在token，直接强制跳转到/login
  if (!token) return next('/login')
  // 如果存在 token，允许访问
  return next()
})

export default router

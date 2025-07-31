import { createRouter, createWebHistory } from 'vue-router'
import { getToken } from '@/utils/cookie'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/oj/login',
      name: 'login',
      component: () => import('@/views/Login.vue')
    },
    {
      path:"/",
      redirect:'/oj/login',
    },
    {
      path: '/oj/layout',
      name: 'layout',
      component: () => import('@/views/Layout.vue'),
      children: [
        {
          path: 'question',
          name: 'question',
          component: () => import('@/views/Question.vue')
        },
        {
          path: 'exam',
          name: 'exam',
          component: () => import('@/views/Exam.vue')
        },
        {
          path: 'updateExam',
          name: 'updateExam',
          component: () => import('@/views/UpdateExam.vue')
        },
        {
          path: 'cuser',
          name: 'cuser',
          component: () => import('@/views/Cuser.vue')
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (getToken()) {  //已经登陆过
    /* has token*/
    if (to.path === '/oj/login') {
      next({ path: '/oj/layout/question' })
    } else {
      next()
    }
  } else {
    if (to.path !== '/oj/login') {
      next({path:'/oj/login'})
    } else {
      next()
    }
  }
})

export default router



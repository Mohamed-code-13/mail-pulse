import { createRouter, createWebHistory } from 'vue-router'
import LoginForm from '../views/LoginForm.vue'
import SignUpForm from '../views/SignUpForm.vue'
import Home from '../views/Home.vue'
import Inbox from '../views/Inbox.vue'
import Contacts from '../views/Contacts.vue'
import Folders from '../views/Folders.vue'
import FolderDetails from '../views/FolderDetails.vue'
import EmailDetails from '../views/EmailDetails.vue'
import store from '@/store'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginForm
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignUpForm
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: {
        requiredAuth: true
      },
      children: [
        {
          path: 'inbox',
          name: 'inbox',
          component: Inbox,
          meta: {
            requiredAuth: true
          }
        },
        {
          path: 'inbox/:id',
          name: 'email-details',
          component: EmailDetails,
          props: true,
          meta: {
            requiredAuth: true
          }
        },
        {
          path: 'sent',
          // name: 'inbox',
          component: Inbox,
          meta: {
            requiredAuth: true
          }
        },
        {
          path: 'contacts',
          name: 'contacts',
          component: Contacts,
          meta: {
            requiredAuth: true
          }
        },
        {
          path: 'draft',
          // name: 'inbox',
          component: Inbox,
          meta: {
            requiredAuth: true
          }
        },
        {
          path: 'starred',
          // name: 'inbox',
          component: Inbox,
          meta: {
            requiredAuth: true
          }
        },
        {
          path: 'folders',
          name: 'folders',
          component: Folders,
          meta: {
            requiredAuth: true
          }
        },
        {
          path: 'folders/:name',
          name: 'folder-details',
          component: FolderDetails,
          props: true,
          meta: {
            requiredAuth: true
          }
        },
        {
          path: 'trash',
          // name: 'inbox',
          component: Inbox,
          meta: {
            requiredAuth: true
          }
        }
      ]
    },
    {
      path: '/:catchAll(.*)',
      redirect: '/login'
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiredAuth)) {
    if (store.getters.token == null) {
      next({ name: 'login' })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router

import Vue from "vue";
import Router from "vue-router";

const Home = () => import("@/views/home/Home"); //首页
const Schedule = () => import("@/views/schedule/Schedule");//我的兑现
const Remind = () => import("@/views/remind/Remind"); //兑现提醒
const Assess = () => import("@/views/assess/Assess");//兑现评估
const Progress = () => import("@/views/progress/Progress");//查看进度
const Match = () => import("@/views/match/Match");//当前差距详情
const Policy = () => import("@/views/policy/Policy");//可享受政策详情
const Instruction = () => import("@/views/instruction/Instruction"); //政策兑现步骤说明
const NotCompany = () => import("@/views/notcompany/NotCompany"); //如果兑现评估查找的不是当前绑定企业
const ChooseCompany = () => import("@/views/choosecompany/ChooseCompany")//选择企业
const CompanyList = () => import("@/views/companylist/CompanyList") //模糊查询企业列表
const Browse = () => import("@/views/browse/Browse") //政策原文(pdf)浏览页面
const Payment = () => import("@/views/payment/Payment") //免审即享跳转的页面
const Accredit = () => import("@/views/accredit/Accredit")//企业授权页面
const Capital = () => import("@/views/capital/Capital") //资金
const Original = () => import("@/views/original/Original")// 政策原文
const Enterprise = () => import("@/views/enterprise/Enterprise") //一企来
const Suggestion = () => import("@/views/suggestion/Suggestion")//意见与反馈
const Publish = () => import("@/views/publish/Publish")//我要发表

Vue.use(Router);

const routes = [
  {
    path: "/",
    redirect: "/home"
  },
  {
    path: "/home",
    name: "Home",
    component: Home
  },
  {
    path: "/schedule",
    name: "Schedule",
    component: Schedule,
    beforeEnter(to, from, next) {
      const { isBind } = sessionStorage;
      isBind ? next() : next({ name: "Home" })
    },
  },
  {
    path: "/remind",
    name: "Remind",
    component: Remind,
    beforeEnter(to, from, next) {
      const { isBind } = sessionStorage;
      isBind ? next() : next({ name: "Home" })
    },
  },
  {
    path: "/capital",
    name: "Capital",
    component: Capital,
  },
  {
    path: "/assess",
    name: "Assess",
    component: Assess
  },
  {
    path: "/progress",
    name: "Progress",
    component: Progress,
    beforeEnter(to, from, next) {
      const { isBind } = sessionStorage;
      isBind ? next() : next({ name: "Home" })
    },
  },
  {
    path: "/match",
    name: "Match",
    component: Match,
    beforeEnter(to, from, next) {
      const { isBind } = sessionStorage;
      isBind ? next() : next({ name: "Home" })
    },
  },
  {
    path: "/policy",
    name: "Policy",
    component: Policy,
    beforeEnter(to, from, next) {
      const { isBind } = sessionStorage;
      isBind ? next() : next({ name: "Home" })
    },
  },
  {
    path: "/instruction",
    name: "Instruction",
    component: Instruction
  },
  {
    path: "/enterprise",
    name: "Enterprise",
    component: Enterprise
  },
  {
    path: "/suggestion",
    name: "Suggestion",
    component: Suggestion
  },
  {
    path: "/publish",
    name: "Publish",
    component: Publish
  },
  {
    path: "/notcompany",
    name: "NotCompany",
    component: NotCompany,
    beforeEnter(to, from, next) {
      const { isBind } = sessionStorage;
      isBind ? next() : next({ name: "Home" })
    },
  },
  {
    path: "/choosecompany",
    name: "ChooseCompany",
    component: ChooseCompany
  },
  {
    path: "/companylist",
    name: "CompanyList",
    component: CompanyList
  },
  {
    path: "/browse",
    name: "Browse",
    component: Browse,
  },
  {
    path: "/payment",
    name: "Payment",
    component: Payment,
  },
  {
    path: "/accredit",
    name: "Accredit",
    component: Accredit,
  },
  {
    path: "/original",
    name: "Original",
    component: Original,
  },
]

const router = new Router({
  base: '/jymd',
  routes
});

// router.beforeEach((to, from, next) => {
//   console.log(to, from);
//   const { isLogin } = sessionStorage;
//   const { name } = to;
//   // const isLoginOrRegister = name === "Login" || name === "Register";
//   isLogin ? next() : next({ name: "Home" });
// });


export default router;
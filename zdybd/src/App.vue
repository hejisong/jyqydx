<template>
  <CoreApp :query-menu="queryMenu" app-name="南大先腾" />
</template>

<script>
import CoreApp from '@centit/module-core/src/App'
export default {
  name: 'App',
  components: {
    CoreApp,
  },
  methods: {
    async queryMenu () {
      // const dashboard = {
      //   id: 'Home',
      //   url: '/home',
      //   icon: 'md-home',
      //   text: '首页',
      // }
      // const about = {
      //   id: 'About',
      //   url: '/about',
      //   icon: 'md-people',
      //   text: '关于',
      // }
      const Policy = {
        id: 'qyzc',
        url: '/Menu',
        icon: 'md-people',
        text: '企业政策',
        show: true,
        children: [
          {
            id: 'Uf_XNsJ7RF-W8L9WFI6klA',
            url: '/Menu/Uf_XNsJ7RF-W8L9WFI6klA',
            text: '政策文件',
          },
        ]
      }
      const Business = {
        id: 'glqy',
        url: '/Menu',
        icon: 'md-people',
        text: '管理企业',
        show: true,
        children: [
          {
            id: 'K6G1huXzSBOVG-1i7-aAHA',
            url: '/Menu/K6G1huXzSBOVG-1i7-aAHA',
            text: '企业专利信息',
          },
          {
            id: 'yQSnKSkESg2lB-CpGIsz0g',
            url: '/Menu/yQSnKSkESg2lB-CpGIsz0g',
            text: '高新技术企业认定信息',
          },
          {
            id: 'Rlmi5AuCRqiCF_NHSWlZuA',
            url: '/Menu/Rlmi5AuCRqiCF_NHSWlZuA',
            text: '企业基本信息',
          },
          {
            id: 'WWYbmQXLS1a2SasBunRMww',
            url: '/Menu/WWYbmQXLS1a2SasBunRMww',
            text: '企业实验室认定信息',
          },
          {
            id: '-_2ZeCBCTVakhf5-8-gk6',
            url: '/Menu/-_2ZeCBCTVakhf5-8-gk6Q',
            text: '企业经营认定信息',
          },
          {
            id: '0y1-7ym2TTiom0Q7K-NLjA',
            url: '/Menu/0y1-7ym2TTiom0Q7K-NLjA',
            text: '运营方孵化企业信息',
          },
          {
            id: 'S9X7GbwJTMuXhDFZvnXb4w',
            url: '/Menu/S9X7GbwJTMuXhDFZvnXb4w',
            text: '企业项目信息',
          },
          {
            id: 'b3iiVVN1SwWkK_aRnFI1hw',
            url: '/Menu/b3iiVVN1SwWkK_aRnFI1hw',
            text: '高新技术企业信息',
          },
          {
            id: 'fTSMDZi1ScCyQ7uMot2T0g',
            url: '/Menu/fTSMDZi1ScCyQ7uMot2T0g',
            text: '企业营收信息',
          },
          {
            id: 'zyLAjlO3QxCAhD7KI_EZJg',
            url: '/Menu/zyLAjlO3QxCAhD7KI_EZJg',
            text: '企业办公补贴信息',
          },
          {
            id: 'GxNFdbAxQTS4Bj85VJ3T_g',
            url: '/Menu/GxNFdbAxQTS4Bj85VJ3T_g',
            text: '人才信息',
          },
          {
            id: 'WzbHUfk0SVKaPOJf3vPiAA',
            url: '/Menu/WzbHUfk0SVKaPOJf3vPiAA',
            text: '落户奖励信息',
          },
          {
            id: 'RBAtwIaXRoudnEGrWpuoPw',
            url: '/Menu/RBAtwIaXRoudnEGrWpuoPw',
            text: '股权投资管理排名',
          },
          {
            id: 'mH5V_Q-jTViq04MDWrKZFQ',
            url: '/Menu/mH5V_Q-jTViq04MDWrKZFQ',
            text: '股权融资与信贷融资',
          },
          {
            id: 'qx87EO2bRUyJ3n_zrxb4Ew',
            url: '/Menu/qx87EO2bRUyJ3n_zrxb4Ew',
            text: '企业社会团体(协会)',
          },
          {
            id: 'EUADdnynRNGSliH1y_YVDQ',
            url: '/Menu/EUADdnynRNGSliH1y_YVDQ',
            text: '企业大型会议',
          },
          {
            id: '22gsq8ZxRG6GOd39t7vNaw',
            url: '/Menu/22gsq8ZxRG6GOd39t7vNaw',
            text: '产业创新信息',
          },
          {
            id: 'vXNRiBm2ThqAYG_U3HHjeg',
            url: '/Menu/vXNRiBm2ThqAYG_U3HHjeg',
            text: '开放创新信息',
          }
        ]
      }
      return [Policy, Business]
    },
  },
}
</script>

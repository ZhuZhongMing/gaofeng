<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('公司')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="pcaSlot" slot-scope="text">
          <div>{{ getPcaText(text) }}</div>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <mbp-customer-modal ref="modalForm" @ok="modalFormOk"></mbp-customer-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MbpCustomerModal from './modules/MbpCustomerModal'
  import { loadCategoryData } from '@/api/api'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import Area from '@/components/_util/Area'

  export default {
    name: 'MbpCustomerList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      MbpCustomerModal
    },
    data () {
      return {
        description: '公司管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'名称',
            align:"center",
            dataIndex: 'customerName'
          },
         /* {
            title:'简称',
            align:"center",
            dataIndex: 'abcName'
          },
          {
            title:'检索码',
            align:"center",
            dataIndex: 'indexCode'
          },
          {
            title:'isSupplier',
            align:"center",
            dataIndex: 'isSupplier'
          },
          {
            title:'isClient',
            align:"center",
            dataIndex: 'isClient'
          },*/
          {
            title:'行业类型',
            align:"center",
            dataIndex: 'customerType',
            customRender: (text) => (text ? filterMultiDictText(this.dictOptions['customerType'], text) : '')
          },
          /*{
            title:'categoryType',
            align:"center",
            dataIndex: 'categoryType'
          },*/
          {
            title:'地区',
            align:"center",
            dataIndex: 'customerArea',
            scopedSlots: {customRender: 'pcaSlot'}
          },
          /*{
            title:'firstCoDate',
            align:"center",
            dataIndex: 'firstCoDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'categoryType2',
            align:"center",
            dataIndex: 'categoryType2'
          },
          {
            title:'isAssist',
            align:"center",
            dataIndex: 'isAssist'
          },*/
          {
            title:'地址',
            align:"center",
            dataIndex: 'customerAddress'
          },
          /*{
            title:'postCode',
            align:"center",
            dataIndex: 'postCode'
          },
          {
            title:'customerManager',
            align:"center",
            dataIndex: 'customerManager'
          },*/
          {
            title:'联系人',
            align:"center",
            dataIndex: 'contactPerson'
          },
          {
            title:'电话',
            align:"center",
            dataIndex: 'telephone'
          },
          {
            title:'邮箱',
            align:"center",
            dataIndex: 'email'
          },
          /*{
            title:'传真',
            align:"center",
            dataIndex: 'fax'
          },
          {
            title:'银行账号',
            align:"center",
            dataIndex: 'bank'
          },
          {
            title:'account',
            align:"center",
            dataIndex: 'account'
          },
          {
            title:'paymentKind',
            align:"center",
            dataIndex: 'paymentKind'
          },
          {
            title:'paymentCircle',
            align:"center",
            dataIndex: 'paymentCircle'
          },
          {
            title:'currency',
            align:"center",
            dataIndex: 'currency'
          },
          {
            title:'税号',
            align:"center",
            dataIndex: 'taxNo'
          },
          {
            title:'license',
            align:"center",
            dataIndex: 'license'
          },
          {
            title:'creditGrade',
            align:"center",
            dataIndex: 'creditGrade'
          },
          {
            title:'creditLimit',
            align:"center",
            dataIndex: 'creditLimit'
          },
          {
            title:'salesman',
            align:"center",
            dataIndex: 'salesman'
          },
          {
            title:'customerStatus',
            align:"center",
            dataIndex: 'customerStatus'
          },
          {
            title:'description',
            align:"center",
            dataIndex: 'description'
          },*/
          {
            title:'网址',
            align:"center",
            dataIndex: 'webSite'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'disp'
          },
          {
            title:'创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title:'创建时间',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title:'修改人',
            align:"center",
            dataIndex: 'updateBy'
          },
          {
            title:'修改时间',
            align:"center",
            dataIndex: 'updateTime'
          },
          /*{
            title:'是否删除',
            align:"center",
            dataIndex: 'delFlag_dictText'
          },*/
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/system/mbpCustomer/list",
          delete: "/system/mbpCustomer/delete",
          deleteBatch: "/system/mbpCustomer/deleteBatch",
          exportXlsUrl: "/system/mbpCustomer/exportXls",
          importExcelUrl: "system/mbpCustomer/importExcel",

        },
        dictOptions:{},
        pcaData:''
      }
    },
    created() {
      this.pcaData = new Area()
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      getPcaText(code){
        return this.pcaData.getText(code);
      },
      initDictConfig(){
        loadCategoryData({code:'B03'}).then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'customerType', res.result)
          }
        })
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>
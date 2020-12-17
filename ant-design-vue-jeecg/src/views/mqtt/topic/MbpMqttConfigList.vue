<template>
  <a-card class="j-inner-table-wrapper" :bordered="false">

    <!-- 查询区域 begin -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域 end -->

    <!-- 操作按钮区域 begin -->
    <div class="table-operator">
      <a-button type="primary" icon="plus" @click="handleAdd">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('MQTT配置信息')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>

      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
            <a-icon type="delete"/>
            <span>删除</span>
          </a-menu-item>
        </a-menu>
        <a-button>
          <span>批量操作</span>
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>
    <!-- 操作按钮区域 end -->

    <!-- table区域 begin -->
    <div>

      <a-alert type="info" showIcon style="margin-bottom: 16px;">
        <template slot="message">
          <span>已选择</span>
          <a style="font-weight: 600;padding: 0 4px;">{{ selectedRowKeys.length }}</a>
          <span>项</span>
          <a style="margin-left: 24px" @click="onClearSelected">清空</a>
        </template>
      </a-alert>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        class="j-table-force-nowrap"
        :scroll="{x:true}"
        :loading="loading"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :expandedRowKeys="expandedRowKeys"
        :rowSelection="{selectedRowKeys, onChange: onSelectChange}"
        @expand="handleExpand"
        @change="handleTableChange"
      >

        <!-- 内嵌table区域 begin -->
        <template slot="expandedRowRender" slot-scope="record">
          <a-tabs tabPosition="top">
            <a-tab-pane tab="主题" key="mbpMqttTopics" forceRender>
              <mbp-mqtt-topics-sub-table :record="record"/>
            </a-tab-pane>
          </a-tabs>
        </template>
        <!-- 内嵌table区域 end -->

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>

        <template slot="imgSlot" slot-scope="text">
          <div style="font-size: 12px;font-style: italic;">
            <span v-if="!text">无图片</span>
            <img v-else :src="getImgView(text)" alt="" style="max-width:80px;height:25px;"/>
          </div>
        </template>

        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            ghost
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)"
          >
            <span>下载</span>
          </a-button>
        </template>

        <template slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              <span>更多 <a-icon type="down"/></span>
            </a>
            <a-menu slot="overlay">
              <a-menu-item v-if="record.delFlag == 0">
                <a-popconfirm title="确定开始吗?" @confirm="handleStart(record)">
                  <a>开启订阅</a>
                </a-popconfirm>
              </a-menu-item>
              <a-menu-item v-if="record.delFlag == 1">
                <a-popconfirm title="确定取消吗?" @confirm="handleStop(record)">
                  <a>取消订阅</a>
                </a-popconfirm>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>

        </template>

      </a-table>
    </div>
    <!-- table区域 end -->

    <!-- 表单区域 -->
    <mbp-mqtt-config-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MbpMqttConfigModal from './modules/MbpMqttConfigModal'
  import MbpMqttTopicsSubTable from './subTables/MbpMqttTopicsSubTable'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import '@/assets/less/TableExpand.less'
  import { getAction } from '@/api/manage'


  export default {
    name: 'MbpMqttConfigList',
    mixins: [JeecgListMixin],
    components: {
      MbpMqttConfigModal,
      MbpMqttTopicsSubTable,
    },
    data() {
      return {
        description: 'MQTT配置信息列表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            key: 'rowIndex',
            width: 60,
            align: 'center',
            customRender: (t, r, index) => parseInt(index) + 1
          },
          {
            title: '公司名称',
            align: 'center',
            dataIndex: 'customerId_dictText'
          },
          {
            title: '主机地址(例：tcp://127.0.0.1:1883)',
            align: 'center',
            dataIndex: 'mqttHost',
          },
          {
            title: '用户名',
            align: 'center',
            dataIndex: 'mqttUsername',
          },
          {
            title: '密码',
            align: 'center',
            dataIndex: 'mqttPassword',
          },
          {
            title: '客户端ID',
            align: 'center',
            dataIndex: 'mqttClientId',
          },
          {
            title: '说明',
            align: 'center',
            dataIndex: 'disp',
          },
          {
            title: '创建人',
            align: 'center',
            dataIndex: 'createBy',
          },
          {
            title: '创建时间',
            align: 'center',
            dataIndex: 'createTime',
          },
          {
            title: '修改人',
            align: 'center',
            dataIndex: 'updateBy',
          },
          {
            title: '修改时间',
            align: 'center',
            dataIndex: 'updateTime',
          },
          /*{
            title: '是否删除',
            align: 'center',
            dataIndex: 'delFlag_dictText'
          },*/
          {
            title: '操作',
            dataIndex: 'action',
            align: 'center',
            width:147,
            scopedSlots: { customRender: 'action' },
          },
        ],
        // 字典选项
        dictOptions: {},
        // 展开的行test
        expandedRowKeys: [],
        url: {
          list: '/system/mbpMqttConfig/list',
          delete: '/system/mbpMqttConfig/delete',
          deleteBatch: '/system/mbpMqttConfig/deleteBatch',
          exportXlsUrl: '/system/mbpMqttConfig/exportXls',
          importExcelUrl: '/system/mbpMqttConfig/importExcel',
          start: "/system/mbpMqttConfig/startSub", // 开启订阅
          stop: "/system/mbpMqttConfig/stopSub" // 开启订阅
        },
      }
    },
    computed: {
      importExcelUrl() {
        return window._CONFIG['domianURL'] + this.url.importExcelUrl
      }
    },
    methods: {
      // 开启订阅
      handleStart(recode) {
        getAction(this.url.start,recode).then((res)=>{
          if(res.success){
            this.$notification.success({
              message: '操作成功',
              description: res.result,
            });
            this.loadData()
          } else {
            this.$notification.warning({
              message: '操作失败',
              description: res.message,
            });
          }
        });
      },
      // 关闭订阅
      handleStop(recode) {
        getAction(this.url.stop,recode).then((res)=>{
          if(res.success){
            this.$notification.success({
              message: '操作成功',
              description: res.result,
            });
            this.loadData()
          } else {
            this.$notification.warning({
              message: '操作失败',
              description: res.message,
            });
          }
        });
      },
      initDictConfig() {
      },

      handleExpand(expanded, record) {
        this.expandedRowKeys = []
        if (expanded === true) {
          this.expandedRowKeys.push(record.id)
        }
      },
    }
  }
</script>
<style lang="less" scoped>
  @import '~@assets/less/common.less';
</style>
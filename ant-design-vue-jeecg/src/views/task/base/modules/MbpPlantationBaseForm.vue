<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['baseName', validatorRules.baseName]" placeholder="请输入名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['baseAddress', validatorRules.baseAddress]" placeholder="请输入地址"  @click="showMap">
                <!--<a-input v-decorator="['baseAddress']" placeholder="请输入地址" style="width: 80%;" disabled>-->
                <a-tooltip slot="suffix" title="点击选择地址">
                  <a-icon type="environment" style="color: rgba(0,0,0,.45)" />
                </a-tooltip>
              </a-input>
              <!--<a-button style="width: 20%;" @click="showMap">地图</a-button>-->
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="经度" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['lng', validatorRules.lng]" placeholder="请输入经度" disabled></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="纬度" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['lat', validatorRules.lat]" placeholder="请输入纬度" disabled></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-textarea v-decorator="['disp']" rows="4" placeholder="请输入备注"/>
            </a-form-item>
          </a-col>
          <!--<a-col :span="24">
            <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['createBy']" placeholder="请输入创建人"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择创建时间" v-decorator="['createTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="修改人" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['updateBy']" placeholder="请输入修改人"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="修改时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择修改时间" v-decorator="['updateTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="是否删除" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['delFlag']" :trigger-change="true" dictCode="del_flag" placeholder="请选择是否删除"/>
            </a-form-item>
          </a-col>-->
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
    <!--地图弹窗-->
    <a-modal
      :width="800"
      title="地图"
      :visible="mapVisible"
      :confirm-loading="confirmLoading"
      @ok="mapOk"
      @cancel="mapCancel"
    >
      <SelectAddressMap ref="Map"></SelectAddressMap>
    </a-modal>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import SelectAddressMap from "@/views/task/components/SelectAddressMap"

  export default {
    name: 'MbpPlantationBaseForm',
    components: {
      JFormContainer,
      JDate,
      JDictSelectTag,
      SelectAddressMap
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：true流程表单 false普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        form: this.$form.createForm(this),
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          baseName: {
            rules: [
              { required: true, message: '请输入名称!'},
            ]
          },
          baseAddress: {
            rules: [
              { required: true, message: '请选择地址!'},
            ]
          },
          lng: {
            rules: [
              { required: true, message: '请输入经度!'},
            ]
          },
          lat: {
            rules: [
              { required: true, message: '请输入纬度!'},
            ]
          },
        },
        url: {
          add: "/system/mbpPlantationBase/add",
          edit: "/system/mbpPlantationBase/edit",
          queryById: "/system/mbpPlantationBase/queryById"
        },
        lat: '',
        lng: '',
        address: '',
        mapVisible: false,
        confirmLoading: false,
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
    },
    methods: {
      mapOk() {
        this.$nextTick(() => {
          this.form.setFieldsValue({"baseAddress" : this.$refs.Map.address});
          this.form.setFieldsValue({"lng" : this.$refs.Map.centerStr.lng});
          this.form.setFieldsValue({"lat" : this.$refs.Map.centerStr.lat});
        })
        this.mapVisible = false;
        this.lng = this.$refs.Map.centerStr.lng
        this.lat = this.$refs.Map.centerStr.lat
        this.address = this.$refs.Map.address
        /*console.log('经度：' + this.$refs.Map.centerStr.lng);
        console.log('纬度：' + this.$refs.Map.centerStr.lat);
        console.log('地址：' + this.$refs.Map.address);*/
      },
      mapCancel() {
        this.lng = ""
        this.lat = ""
        this.address = ""
        this.mapVisible = false;
      },
      /*地址选择*/
      showMap() {

        this.mapVisible = true;
        if (this.lat != "") {
          setTimeout(() => {
            this.$refs.Map.changeAddress(this.lng, this.lat, this.address)
          },500)
        }
      },
      add () {
        this.edit({});
      },
      edit (record) {
        if (record.id != undefined && record.id != null && record.id != "") {
          this.lng = record.lng
          this.lat = record.lat
          this.address = record.baseAddress
        }
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'baseName','baseAddress','lng','lat','disp','createBy','createTime','updateBy','updateTime','delFlag'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }

        })
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'baseName','baseAddress','lng','lat','disp','createBy','createTime','updateBy','updateTime','delFlag'))
      },
    }
  }
</script>
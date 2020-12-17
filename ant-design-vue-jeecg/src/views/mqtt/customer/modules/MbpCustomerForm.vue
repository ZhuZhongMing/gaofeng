<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['customerName', validatorRules.customerName]" placeholder="请输入名称"></a-input>
            </a-form-item>
          </a-col>
          <!--<a-col :span="24">
            <a-form-item label="简称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['abcName']" placeholder="请输入简称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="检索码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['indexCode']" placeholder="请输入检索码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="isSupplier" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['isSupplier']" placeholder="请输入isSupplier" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="isClient" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['isClient']" placeholder="请输入isClient" style="width: 100%"/>
            </a-form-item>
          </a-col>-->
          <a-col :span="24">
            <a-form-item label="行业类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-category-select v-decorator="['customerType', validatorRules.customerType]" pcode="B03" placeholder="请选择行业类型" />
            </a-form-item>
          </a-col>
          <!--<a-col :span="24">
            <a-form-item label="categoryType" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['categoryType']" placeholder="请输入categoryType"></a-input>
            </a-form-item>
          </a-col>-->
          <a-col :span="24">
            <a-form-item label="地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
             <j-area-linkage type="cascader" v-decorator="['customerArea']" placeholder="请输入省市区"/>
            </a-form-item>
          </a-col>
          <!--<a-col :span="24">
            <a-form-item label="firstCoDate" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择firstCoDate" v-decorator="['firstCoDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="categoryType2" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['categoryType2']" placeholder="请输入categoryType2"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="isAssist" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['isAssist']" placeholder="请输入isAssist" style="width: 100%"/>
            </a-form-item>
          </a-col>-->
          <a-col :span="24">
            <a-form-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-textarea v-decorator="['customerAddress']" rows="4" placeholder="请输入地址"/>
            </a-form-item>
          </a-col>
          <!--<a-col :span="24">
            <a-form-item label="postCode" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['postCode']" placeholder="请输入postCode"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="customerManager" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['customerManager']" placeholder="请输入customerManager"></a-input>
            </a-form-item>
          </a-col>-->
          <a-col :span="24">
            <a-form-item label="联系人" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['contactPerson']" placeholder="请输入联系人"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="电话" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['telephone', validatorRules.telephone]" placeholder="请输入电话"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['email', validatorRules.email]" placeholder="请输入邮箱"></a-input>
            </a-form-item>
          </a-col>
          <!--<a-col :span="24">
            <a-form-item label="传真" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['fax']" placeholder="请输入传真"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="银行账号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['bank']" placeholder="请输入银行账号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="account" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['account']" placeholder="请输入account"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="paymentKind" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['paymentKind']" placeholder="请输入paymentKind"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="paymentCircle" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['paymentCircle']" placeholder="请输入paymentCircle" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="currency" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['currency']" placeholder="请输入currency"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="税号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['taxNo']" placeholder="请输入税号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="license" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['license']" placeholder="请输入license"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="creditGrade" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['creditGrade']" placeholder="请输入creditGrade"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="creditLimit" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['creditLimit']" placeholder="请输入creditLimit" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="salesman" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['salesman']" placeholder="请输入salesman"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="customerStatus" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['customerStatus']" placeholder="请输入customerStatus" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="description" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['description']" placeholder="请输入description"></a-input>
            </a-form-item>
          </a-col>-->
          <a-col :span="24">
          <a-form-item label="网址" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input v-decorator="['webSite', validatorRules.webSite]" placeholder="请输入网址"></a-input>
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
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import JCategorySelect from '@/components/jeecg/JCategorySelect'
  import JAreaLinkage from '@comp/jeecg/JAreaLinkage'

  export default {
    name: 'MbpCustomerForm',
    components: {
      JFormContainer,
      JDate,
      JDictSelectTag,
      JAreaLinkage,
      JCategorySelect,
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
          customerName: {
            rules: [
              { required: true, message: '请输入名称!'},
            ]
          },
          customerType: {
            rules: [
              { required: true, message: '请输入行业类型!'},
            ]
          },
          telephone: {
            rules: [
              { required: false},
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
            ]
          },
          email: {
            rules: [
              { required: false},
              { pattern: /^([\w]+\.*)([\w]+)@[\w]+\.\w{3}(\.\w{2}|)$/, message: '请输入正确的电子邮件!'},
            ]
          },
          webSite: {
            rules: [
              { required: false},
              { pattern: /^(?:([A-Za-z]+):)?(\/{0,3})([0-9.\-A-Za-z]+)(?::(\d+))?(?:\/([^?#]*))?(?:\?([^#]*))?(?:#(.*))?$/, message: '请输入正确的网址!'},
            ]
          },
        },
        url: {
          add: "/system/mbpCustomer/add",
          edit: "/system/mbpCustomer/edit",
          queryById: "/system/mbpCustomer/queryById"
        }
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
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'customerName','abcName','indexCode','isSupplier','isClient','customerType','categoryType','customerArea','firstCoDate','categoryType2','isAssist','customerAddress','postCode','customerManager','contactPerson','telephone','email','fax','bank','account','paymentKind','paymentCircle','currency','taxNo','license','creditGrade','creditLimit','salesman','customerStatus','description','disp','webSite','createBy','createTime','updateBy','updateTime','delFlag'))
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
        this.form.setFieldsValue(pick(row,'customerName','abcName','indexCode','isSupplier','isClient','customerType','categoryType','customerArea','firstCoDate','categoryType2','isAssist','customerAddress','postCode','customerManager','contactPerson','telephone','email','fax','bank','account','paymentKind','paymentCircle','currency','taxNo','license','creditGrade','creditLimit','salesman','customerStatus','description','disp','webSite','createBy','createTime','updateBy','updateTime','delFlag'))
      },
      handleCategoryChange(value,backObj){
        this.form.setFieldsValue(backObj)
      }
    }
  }
</script>
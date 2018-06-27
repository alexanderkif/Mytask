webpackJsonp([1],{"/626":function(t,e){},"2wno":function(t,e){},"3TcT":function(t,e){},"6STh":function(t,e){},"6nfk":function(t,e){},"90VL":function(t,e){},"9DHQ":function(t,e){},"A+FS":function(t,e){},BCfr:function(t,e){},FbEL:function(t,e){},"HNa/":function(t,e){},JLzs:function(t,e){},"N+IJ":function(t,e){},NHnr:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=o("7+uW"),r=o("d7EF"),s=o.n(r),n={name:"Count",data:function(){return{valid:!0,strSummRules:[function(t){return!!t||"Обязательное поле"},function(t){return t&&!/^0|\D/.test(t)||"Введите целое число"}],squareRules:[function(t){return!!t||"Обязательное поле"},function(t){return t&&!/^0/.test(t)||"Числа не начинаются с 0"},function(t){return t&&/\d+\.\d$/.test(t)||"Введите число с одним знаком после точки"}],StartDateRules:[function(t){return!!t||"Обязательное поле"},function(t){return t&&/^(19|20)\d\d-((0[1-9]|1[012])-(0[1-9]|[12]\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)$/.test(t)||"с 1900-01-01 по 2099-12-31"}],DateRules:[function(t){return!!t||"Обязательное поле"},function(t){return t&&/^(19|20)\d\d-((0[1-9]|1[012])-(0[1-9]|[12]\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)$/.test(t)||"с 1900-01-01 по 2099-12-31"}],typesObject:["Квартира","Дом","Комната"],startDateMenu:!1,endDateMenu:!1}},methods:{changeStartDate:function(){this.dogovor.enddate=null},today:function(){return(new Date).toISOString().substr(0,10)},minDate:function(){if(this.dogovor.startdate){var t=this.dogovor.startdate.split("-"),e=s()(t,3),o=e[0],a=e[1],r=e[2];return new Date(parseInt(o),parseInt(a)-1,parseInt(r)+2).toISOString().substr(0,10)}return null},maxDate:function(){if(this.dogovor.startdate){var t=this.dogovor.startdate.split("-"),e=s()(t,3),o=e[0],a=e[1],r=e[2];return new Date(parseInt(o)+1,parseInt(a)-1,parseInt(r)).toISOString().substr(0,10)}return null},yearsDateObject:function(){for(var t=[],e=parseInt((new Date).toISOString().substr(0,4));e>1900;e--)t.push(e.toString());return t},getCount:function(){if(Date.parse(this.dogovor.startdate)-Date.parse((new Date).toISOString().substr(0,10))<0)return this.dogovor.startdate=(new Date).toISOString().substr(0,10),this.dogovor.enddate=null,null;if(Date.parse(this.dogovor.enddate)-Date.parse(this.dogovor.startdate)<=0)return this.dogovor.enddate=null,null;if(this.dogovor.countdate=(new Date).toISOString().substr(0,10),this.$refs.form.validate()){var t,e,o;switch(this.dogovor.type){case"Квартира":t=1.7;break;case"Дом":t=1.5;break;default:t=1.3}e=this.dogovor.year>=2015?2:this.dogovor.year>=2e3?1.6:1.3,o=this.dogovor.square>100?2:this.dogovor.square>=50?1.5:1.2;var a=(Date.parse(this.dogovor.enddate)-Date.parse(this.dogovor.startdate))/864e5;this.dogovor.bonus=Math.round(this.dogovor.strsumma/a*t*e*o*100)/100,this.dogovor.countdate=(new Date).toISOString().substr(0,10)}}},computed:{validForm:function(){return this.$store.dispatch("setValidCount",!this.valid),!this.valid},dogovor:function(){return this.$store.getters.getDogovor}},created:function(){this.startDateMenu=!0,this.startDateMenu=!1}},i={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-form",{ref:"form",attrs:{"lazy-validation":""},model:{value:t.valid,callback:function(e){t.valid=e},expression:"valid"}},[o("v-layout",{attrs:{row:"",wrap:"","mt-5":""}},[o("v-flex",{attrs:{xs10:"","offset-xs1":"","text-xs-left":""}},[t._v("\n            РАСЧЕТ\n        ")])],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md10:"","offset-md1":""}},[o("v-divider")],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","mt-3":""}},[o("v-flex",{attrs:{xs12:"",md3:"","offset-md1":""}},[o("v-text-field",{attrs:{rules:t.strSummRules,label:"Страховая сумма",required:""},model:{value:t.dogovor.strsumma,callback:function(e){t.$set(t.dogovor,"strsumma","string"==typeof e?e.trim():e)},expression:"dogovor.strsumma"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md3:"","offset-md1":""}},[o("v-menu",{ref:"startDateMenu",attrs:{"close-on-content-click":!1,"nudge-right":40,"return-value":t.dogovor.startdate,lazy:"",transition:"scale-transition","offset-y":"","full-width":"","min-width":"290px"},on:{"update:returnValue":function(e){t.$set(t.dogovor,"startdate",e)}},model:{value:t.startDateMenu,callback:function(e){t.startDateMenu=e},expression:"startDateMenu"}},[o("v-text-field",{attrs:{slot:"activator",label:"Срок действия с","prepend-icon":"event",rules:t.StartDateRules,required:""},slot:"activator",model:{value:t.dogovor.startdate,callback:function(e){t.$set(t.dogovor,"startdate",e)},expression:"dogovor.startdate"}}),t._v(" "),o("v-date-picker",{attrs:{"first-day-of-week":1,locale:"ru-ru",min:t.today()},on:{input:function(e){t.$refs.startDateMenu.save(t.dogovor.startdate)}},model:{value:t.dogovor.startdate,callback:function(e){t.$set(t.dogovor,"startdate",e)},expression:"dogovor.startdate"}})],1)],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md3:""}},[o("v-menu",{ref:"endDateMenu",attrs:{"close-on-content-click":!1,"nudge-right":40,"return-value":t.dogovor.enddate,transition:"scale-transition","offset-y":"","full-width":"","min-width":"290px"},on:{"update:returnValue":function(e){t.$set(t.dogovor,"enddate",e)}},model:{value:t.endDateMenu,callback:function(e){t.endDateMenu=e},expression:"endDateMenu"}},[o("v-text-field",{attrs:{slot:"activator",label:"Срок действия по","prepend-icon":"event",rules:t.DateRules,required:""},on:{input:function(e){t.$refs.endDateMenu.save(t.dogovor.enddate)}},slot:"activator",model:{value:t.dogovor.enddate,callback:function(e){t.$set(t.dogovor,"enddate",e)},expression:"dogovor.enddate"}}),t._v(" "),o("v-date-picker",{attrs:{"first-day-of-week":1,locale:"ru-ru",min:t.minDate(),max:t.maxDate()},on:{input:function(e){t.$refs.endDateMenu.save(t.dogovor.enddate)}},model:{value:t.dogovor.enddate,callback:function(e){t.$set(t.dogovor,"enddate",e)},expression:"dogovor.enddate"}})],1)],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md3:"","offset-md1":""}},[o("v-select",{attrs:{items:t.typesObject,rules:[function(t){return!!t||"Обязательное поле"}],label:"Тип недвижимости",required:""},model:{value:t.dogovor.type,callback:function(e){t.$set(t.dogovor,"type",e)},expression:"dogovor.type"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md3:"","offset-md1":""}},[o("v-select",{attrs:{id:"yearId",items:t.yearsDateObject(),rules:[function(t){return!!t||"Обязательное поле"}],label:"Год постройки",required:""},model:{value:t.dogovor.year,callback:function(e){t.$set(t.dogovor,"year",e)},expression:"dogovor.year"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md3:"","offset-md1":""}},[o("v-text-field",{attrs:{id:"squareId",rules:t.squareRules,label:"Площадь, кв.м",required:""},model:{value:t.dogovor.square,callback:function(e){t.$set(t.dogovor,"square",e)},expression:"dogovor.square"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",center:""}},[o("v-btn",{attrs:{disabled:t.validForm},on:{click:function(e){t.getCount()}}},[t._v("\n            Рассчитать\n            ")])],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs6:"",md3:"","offset-md1":""}},[o("v-text-field",{attrs:{label:"Дата расчета","prepend-icon":"event",readonly:""},model:{value:t.dogovor.countdate,callback:function(e){t.$set(t.dogovor,"countdate",e)},expression:"dogovor.countdate"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md3:"","offset-md4":""}},[o("v-text-field",{attrs:{label:"Премия","prepend-icon":"attach_money",readonly:""},model:{value:t.dogovor.bonus,callback:function(e){t.$set(t.dogovor,"bonus",e)},expression:"dogovor.bonus"}})],1)],1)],1)},staticRenderFns:[]},l=o("VU/8")(n,i,!1,null,null,null).exports,v={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("v-layout",{attrs:{row:"",wrap:"","mt-5":""}},[o("v-flex",{attrs:{xs10:"","offset-xs1":"","text-xs-left":""}},[t._v("\n        СТРАХОВАТЕЛЬ\n    ")])],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md10:"","offset-md1":""}},[o("v-divider")],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","mt-3":""}},[o("v-flex",{attrs:{xs12:"",md2:"","offset-md1":"","text-xs-center":"","pt-3":""}},[o("v-dialog",{attrs:{fullscreen:"","hide-overlay":"",transition:"dialog-bottom-transition"},model:{value:t.dialog,callback:function(e){t.dialog=e},expression:"dialog"}},[o("v-btn",{attrs:{slot:"activator",small:""},on:{click:t.getStrahovatels},slot:"activator"},[t._v("Выбрать")]),t._v(" "),o("v-card",[o("v-toolbar",[o("v-btn",{attrs:{icon:""},nativeOn:{click:function(e){t.dialog=!1}}},[o("v-icon",[t._v("close")])],1),t._v(" "),o("v-toolbar-title",[t._v("Выбор страхователя")]),t._v(" "),o("v-spacer"),t._v(" "),o("v-toolbar-items",[t.show?t._e():o("v-btn",{attrs:{flat:""},nativeOn:{click:function(e){t.show=!0}}},[t._v("Новый клиент")]),t._v(" "),t.show?o("v-btn",{attrs:{flat:""},nativeOn:{click:function(e){t.show=!1}}},[t._v("Список клиентов")]):t._e()],1)],1),t._v(" "),o("transition",{attrs:{name:"bounce",mode:"out-in"}},[t.show?t._e():o("v-container",[o("v-layout",{attrs:{row:"",wrap:"","mt-5":""}},[o("v-flex",{attrs:{xs8:"","offset-xs2":"","text-xs-left":""}},[t._v("\n                      ПОИСК СТРАХОВАТЕЛЯ\n                  ")])],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",sm8:"","offset-sm2":""}},[o("v-divider")],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","my-3":""}},[o("v-flex",{attrs:{xs4:"",sm2:"","offset-sm2":""}},[o("v-text-field",{attrs:{label:"Фамилия"},on:{change:t.getStrahovatels},model:{value:t.lastname,callback:function(e){t.lastname="string"==typeof e?e.trim():e},expression:"lastname"}})],1),t._v(" "),o("v-flex",{attrs:{xs4:"",sm2:"","offset-sm1":""}},[o("v-text-field",{attrs:{label:"Имя"},on:{change:t.getStrahovatels},model:{value:t.firstname,callback:function(e){t.firstname="string"==typeof e?e.trim():e},expression:"firstname"}})],1),t._v(" "),o("v-flex",{attrs:{xs4:"",sm2:"","offset-sm1":""}},[o("v-text-field",{attrs:{label:"Отчество"},on:{change:t.getStrahovatels},model:{value:t.firstname2,callback:function(e){t.firstname2="string"==typeof e?e.trim():e},expression:"firstname2"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md10:"","offset-md1":"","mb-5":""}},[o("v-data-table",{staticClass:"elevation-1",attrs:{headers:t.headers,items:t.strahovatels,"item-key":"iddog"},scopedSlots:t._u([{key:"items",fn:function(e){return[o("tr",{attrs:{active:e.selected},on:{click:function(o){t.openSelected(e.item)}}},[o("td",{staticClass:"text-xs-right"},[t._v(t._s(e.item.lastname)+" "+t._s(e.item.firstname)+" "+t._s(e.item.firstname2))]),t._v(" "),o("td",{staticClass:"text-xs-center"},[t._v(t._s(e.item.birth))]),t._v(" "),o("td",{staticClass:"text-xs-left"},[t._v(t._s(e.item.passportseria)+" - "+t._s(e.item.passportnumber))])])]}}]),model:{value:t.selected,callback:function(e){t.selected=e},expression:"selected"}})],1)],1)],1)],1),t._v(" "),o("transition",{attrs:{name:"bounce2",duration:{before:500}}},[t.show?o("v-container",[o("v-layout",{attrs:{row:"",wrap:"","mt-5":""}},[o("v-flex",{attrs:{xs8:"","offset-xs2":"","text-xs-left":""}},[t._v("\n                      НОВЫЙ СТРАХОВАТЕЛЬ\n                  ")])],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",sm8:"","offset-sm2":""}},[o("v-divider")],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","my-3":""}},[o("v-flex",{attrs:{xs4:"",sm2:"","offset-sm2":""}},[o("v-text-field",{attrs:{label:"Фамилия"},model:{value:t.lastname,callback:function(e){t.lastname="string"==typeof e?e.trim():e},expression:"lastname"}})],1),t._v(" "),o("v-flex",{attrs:{xs4:"",sm2:"","offset-sm1":""}},[o("v-text-field",{attrs:{label:"Имя"},model:{value:t.firstname,callback:function(e){t.firstname="string"==typeof e?e.trim():e},expression:"firstname"}})],1),t._v(" "),o("v-flex",{attrs:{xs4:"",sm2:"","offset-sm1":""}},[o("v-text-field",{attrs:{label:"Отчество"},model:{value:t.firstname2,callback:function(e){t.firstname2="string"==typeof e?e.trim():e},expression:"firstname2"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","my-3":""}},[o("v-flex",{attrs:{xs12:"",md2:"","offset-md2":""}},[o("v-menu",{ref:"menuBirthDate",attrs:{"close-on-content-click":!1,"nudge-right":40,lazy:"",transition:"scale-transition","offset-y":"","full-width":"","min-width":"290px"},model:{value:t.menuBirthDate1,callback:function(e){t.menuBirthDate1=e},expression:"menuBirthDate1"}},[o("v-text-field",{attrs:{slot:"activator",label:"Дата рождения","prepend-icon":"event",readonly:"",rules:[function(t){return!!t||"Обязательное поле"}],required:""},slot:"activator",model:{value:t.birth,callback:function(e){t.birth=e},expression:"birth"}}),t._v(" "),o("v-date-picker",{ref:"picker",attrs:{max:(new Date).toISOString().substr(0,10),min:"1950-01-01"},on:{change:t.saveBirthDate},model:{value:t.birth,callback:function(e){t.birth=e},expression:"birth"}})],1)],1),t._v(" "),o("v-flex",{attrs:{xs2:"",md2:"","offset-md1":"","pt-4":"","pr-3":"","text-xs-right":""}},[t._v("\n                      Паспорт\n                  ")]),t._v(" "),o("v-flex",{attrs:{xs4:"",md1:""}},[o("v-text-field",{attrs:{label:"Серия",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.passportseria,callback:function(e){t.passportseria=e},expression:"passportseria"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md2:""}},[o("v-text-field",{attrs:{label:"Номер",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.passportnumber,callback:function(e){t.passportnumber=e},expression:"passportnumber"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","my-3":""}},[o("v-flex",{attrs:{xs6:"","text-xs-right":"","pr-3":""}},[o("v-btn",{on:{click:function(e){t.createStrahovatel()}}},[t._v("Сохранить")])],1),t._v(" "),o("v-flex",{attrs:{xs6:"","text-xs-left":"","pl-3":""}},[o("v-btn",{on:{click:function(e){t.dialog=!1}}},[t._v("Отмена")])],1)],1)],1):t._e()],1)],1)],1)],1),t._v(" "),o("v-flex",{attrs:{xs12:"",md6:""}},[o("v-text-field",{attrs:{label:"ФИО","prepend-icon":"person",rules:[function(t){return!!t||"Обязательное поле"}],readonly:""},model:{value:t.fio,callback:function(e){t.fio=e},expression:"fio"}})],1),t._v(" "),o("v-flex",{attrs:{xs12:"",md2:"","text-xs-center":"","pt-3":""}},[o("v-btn",{attrs:{small:""}},[t._v("Изменить")])],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md2:"","offset-md2":""}},[o("v-menu",{ref:"menuBirthDate",attrs:{"close-on-content-click":!1,"nudge-right":40,lazy:"",transition:"scale-transition","offset-y":"","full-width":"","min-width":"290px"},model:{value:t.menuBirthDate,callback:function(e){t.menuBirthDate=e},expression:"menuBirthDate"}},[o("v-text-field",{attrs:{slot:"activator",label:"Дата рождения","prepend-icon":"event",readonly:"",rules:[function(t){return!!t||"Обязательное поле"}],required:""},slot:"activator",model:{value:t.strahovatel.birth,callback:function(e){t.$set(t.strahovatel,"birth",e)},expression:"strahovatel.birth"}}),t._v(" "),o("v-date-picker",{ref:"picker",attrs:{max:(new Date).toISOString().substr(0,10),min:"1950-01-01"},on:{change:t.saveBirthDate},model:{value:t.strahovatel.birth,callback:function(e){t.$set(t.strahovatel,"birth",e)},expression:"strahovatel.birth"}})],1)],1),t._v(" "),o("v-flex",{attrs:{xs2:"",md2:"","offset-md1":"","pt-4":"","pr-3":"","text-xs-right":""}},[t._v("\n          Паспорт\n      ")]),t._v(" "),o("v-flex",{attrs:{xs4:"",md1:""}},[o("v-text-field",{attrs:{label:"Серия",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.strahovatel.passportseria,callback:function(e){t.$set(t.strahovatel,"passportseria",e)},expression:"strahovatel.passportseria"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md2:""}},[o("v-text-field",{attrs:{label:"Номер",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.strahovatel.passportnumber,callback:function(e){t.$set(t.strahovatel,"passportnumber",e)},expression:"strahovatel.passportnumber"}})],1)],1)],1)},staticRenderFns:[]};var d=o("VU/8")({name:"Client",props:{selected:null},data:function(){return{menuBirthDate:!1,dialog:!1,notifications:!1,sound:!0,widgets:!1,lastname:"",firstname:"",firstname2:"",birth:null,passportseria:null,passportnumber:null,show:!1,headers:[{text:"ФИО",align:"right",value:"idstrah"},{text:"Дата рождения",align:"center",value:"regdate"},{text:"Паспорт",align:"left",value:"passport"}]}},computed:{strahovatel:function(){return this.$store.getters.getStrahovatel},fio:function(){return this.strahovatel.lastname?this.strahovatel.lastname+" "+this.strahovatel.firstname+" "+this.strahovatel.firstname2:""},strahovatels:function(){return this.$store.getters.getStrahovatels}},methods:{saveBirthDate:function(t){this.$refs.menuBirthDate.save(t)},getStrahovatels:function(){this.$store.dispatch("getStrahovatels",this.lastname+"="+this.firstname+"="+this.firstname2)},openSelected:function(t){this.dialog=!1,this.$store.dispatch("setStrahovatel",t)},createStrahovatel:function(){this.strahovatel.idstrah=null,this.strahovatel.lastname=this.lastname,this.strahovatel.firstname=this.firstname,this.strahovatel.firstname2=this.firstname2,this.strahovatel.birth=this.birth,this.strahovatel.passportseria=this.passportseria,this.strahovatel.passportnumber=this.passportnumber,this.dialog=!1,this.$store.dispatch("saveStrahovatel",this.strahovatel)},saveStrahovatel:function(){this.dialog=!1,this.$store.dispatch("saveStrahovatel",this.strahovatel)}},watch:{menuBirthDate:function(t){var e=this;t&&this.$nextTick(function(){return e.$refs.picker.activePicker="YEAR"})}}},v,!1,function(t){o("WxiO")},"data-v-b6331d72",null).exports,u={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("v-layout",{attrs:{row:"",wrap:"","mt-5":""}},[o("v-flex",{attrs:{xs10:"","offset-xs1":"","text-xs-left":""}},[t._v("\n            АДРЕС НЕДВИЖИМОСТИ\n        ")])],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md10:"","offset-md1":""}},[o("v-divider")],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","mt-3":""}},[o("v-flex",{attrs:{xs6:"",md2:"","offset-md1":""}},[o("v-select",{attrs:{items:t.states,rules:[function(t){return!!t||"Обязательное поле"}],label:"Государство",required:""},model:{value:t.dogovor.state,callback:function(e){t.$set(t.dogovor,"state",e)},expression:"dogovor.state"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md1:""}},[o("v-text-field",{attrs:{label:"Индекс"},model:{value:t.dogovor.index,callback:function(e){t.$set(t.dogovor,"index",e)},expression:"dogovor.index"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md4:""}},[o("v-text-field",{attrs:{label:"Республика, край, область",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.dogovor.krai,callback:function(e){t.$set(t.dogovor,"krai",e)},expression:"dogovor.krai"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md3:""}},[o("v-text-field",{attrs:{label:"Район"},model:{value:t.dogovor.district,callback:function(e){t.$set(t.dogovor,"district",e)},expression:"dogovor.district"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs6:"",md2:"","offset-md1":""}},[o("v-text-field",{attrs:{label:"Населенный пункт",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.dogovor.town,callback:function(e){t.$set(t.dogovor,"town",e)},expression:"dogovor.town"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md4:""}},[o("v-text-field",{attrs:{label:"Улица",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.dogovor.street,callback:function(e){t.$set(t.dogovor,"street",e)},expression:"dogovor.street"}})],1),t._v(" "),o("v-flex",{attrs:{xs3:"",md1:""}},[o("v-text-field",{attrs:{label:"Дом",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.dogovor.house,callback:function(e){t.$set(t.dogovor,"house",e)},expression:"dogovor.house"}})],1),t._v(" "),o("v-flex",{attrs:{xs3:"",md1:""}},[o("v-text-field",{attrs:{label:"Корпус"},model:{value:t.dogovor.korpus,callback:function(e){t.$set(t.dogovor,"korpus",e)},expression:"dogovor.korpus"}})],1),t._v(" "),o("v-flex",{attrs:{xs3:"",md1:""}},[o("v-text-field",{attrs:{label:"Строение"},model:{value:t.dogovor.stroenie,callback:function(e){t.$set(t.dogovor,"stroenie",e)},expression:"dogovor.stroenie"}})],1),t._v(" "),o("v-flex",{attrs:{xs3:"",md1:""}},[o("v-text-field",{attrs:{label:"Квартира",rules:[function(t){return!!t||"Обязательное поле"}],required:""},model:{value:t.dogovor.flat,callback:function(e){t.$set(t.dogovor,"flat",e)},expression:"dogovor.flat"}})],1)],1)],1)},staticRenderFns:[]},c=o("VU/8")({name:"Estate",data:function(){return{states:["Россия","Белоруссия","Казахстан"]}},computed:{dogovor:function(){return this.$store.getters.getDogovor}}},u,!1,null,null,null).exports,f={name:"Dtable",props:{selected:null},data:function(){return{headers:[{text:"Серия-номер",align:"center",sortable:!1,value:"iddoc"},{text:"Дата заключения",align:"center",value:"regdate"},{text:"Страхователь",align:"left",value:"fullname"},{text:"Премия",align:"right",value:"bonus"},{text:"Срок действия",align:"center",value:"dates"}]}},computed:{dogovors:function(){return this.$store.getters.getAllDogovors}},methods:{openSelected:function(t){this.$store.dispatch("setDogovor",t),this.$store.dispatch("setShowOneDogovor",!0)},fio:function(t){console.log(t),this.$store.dispatch("getStrahovatel",t);var e=this.$store.getters.getStrahovatel;return console.log(e),e.lastname+" "+e.firstname+" "+e.firstname2}},created:function(){this.$store.dispatch("getDogovors")}},m={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("v-layout",{attrs:{row:"",wrap:"","mb-3":""}},[o("v-flex",{attrs:{xs12:"",md10:"","offset-md1":""}},[o("v-data-table",{staticClass:"elevation-1",attrs:{headers:t.headers,items:t.dogovors,"item-key":"iddog"},scopedSlots:t._u([{key:"items",fn:function(e){return[o("tr",{attrs:{active:e.selected},on:{click:function(o){t.openSelected(e.item)}}},[o("td",[t._v(t._s(e.item.iddog))]),t._v(" "),o("td",{staticClass:"text-xs-center"},[t._v(t._s(e.item.regdate))]),t._v(" "),o("td",{staticClass:"text-xs-left"},[t._v(t._s(e.item.fullname))]),t._v(" "),o("td",{staticClass:"text-xs-right"},[t._v(t._s(e.item.bonus))]),t._v(" "),o("td",{staticClass:"text-xs-center"},[t._v(t._s(e.item.startdate)+" - "+t._s(e.item.enddate))])])]}}]),model:{value:t.selected,callback:function(e){t.selected=e},expression:"selected"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","mb-3":""}},[o("v-flex",{attrs:{xs12:"",md10:"","offset-md1":""}})],1)],1)},staticRenderFns:[]},h={components:{Dtable:o("VU/8")(f,m,!1,null,null,null).exports,Count:l,Client:d,Estate:c},computed:{strahovatel:function(){return this.$store.getters.getStrahovatel},isValidCount:function(){return!this.validMainForm||this.$store.getters.getValidCount},dogovor:function(){return this.$store.dispatch("getStrahovatel",this.$store.getters.getDogovor.strahovatelid),this.$store.getters.getDogovor},showOneDogovor:function(){return this.$store.getters.getShowOneDogovor}},data:function(){return{dark:!1,drawer:!1,dogovorSearch:null,validMainForm:!1,dogovorNumberRules:[function(t){return!!t||"Обязательное поле"},function(t){return t&&/^[1-9]\d{5}$/.test(t)||"Введите 6 цифр"}],dogovorSearchRules:[function(t){return!t||/^[1-9]\d{5}$/.test(t)||"Введите 6 цифр"}]}},mounted:function(){},methods:{createDogovor:function(){this.$store.dispatch("createDogovor"),this.$store.dispatch("setShowOneDogovor",!0),this.dogovor.regdate||this.$store.dispatch("setNewRegDate"),this.dogovor.startdate||this.$store.dispatch("setNewStartDate")},getDogovor:function(){6===this.dogovorSearch.length&&/^[1-9]\d{5}$/.test(this.dogovorSearch)&&(this.$store.dispatch("searchDogovor",this.dogovorSearch),this.$store.dispatch("setShowOneDogovor",!0))},dogovorSave:function(){this.validMainForm=this.$refs.mainForm.validate(),this.isValidCount?(console.log("this.isValidCount "+this.isValidCount),alert("Не сохранено")):(this.dogovor.strahovatelid=this.strahovatel.idstrah,this.dogovor.fullname=this.strahovatel.lastname+" "+this.strahovatel.firstname+" "+this.strahovatel.firstname2,this.$store.dispatch("saveDogovor",this.dogovor),this.$store.dispatch("setShowOneDogovor",!1))},toTheDogovorsList:function(){this.$store.dispatch("setShowOneDogovor",!1)}},created:function(){this.startDateMenu=!0,this.startDateMenu=!1},name:"App"},g={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-app",{attrs:{dark:t.dark}},[o("v-navigation-drawer",{attrs:{fixed:"",clipped:"",app:""},model:{value:t.drawer,callback:function(e){t.drawer=e},expression:"drawer"}},[o("v-container",{attrs:{"text-xs-center":""}},[o("v-layout",{attrs:{row:"",wrap:"","my-3":""}},[o("v-flex",{attrs:{xs10:"","offset-xs1":"","text-xs-left":""}},[o("v-btn",{on:{click:t.createDogovor}},[t._v("Создать договор")])],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","my-3":""}},[o("v-flex",{attrs:{xs10:"","offset-xs1":"","text-xs-left":""}},[o("v-text-field",{attrs:{rules:t.dogovorSearchRules,label:"Поиск договора","prepend-icon":"search"},on:{input:t.getDogovor},model:{value:t.dogovorSearch,callback:function(e){t.dogovorSearch="string"==typeof e?e.trim():e},expression:"dogovorSearch"}})],1)],1)],1)],1),t._v(" "),o("v-toolbar",{attrs:{dense:"",fixed:"","clipped-left":"",app:""}},[o("v-toolbar-side-icon",{on:{click:function(e){e.stopPropagation(),t.drawer=!t.drawer}}})],1),t._v(" "),o("v-content",[o("transition",{attrs:{name:"slide-fade",mode:"out-in"}},[t.showOneDogovor?t._e():o("v-container",{key:"table",staticClass:"mt-3",attrs:{"grid-list-md":"","text-xs-center":""}},[o("Dtable")],1),t._v(" "),t.showOneDogovor?o("v-container",{key:"dogovor",staticClass:"mt-3",attrs:{"grid-list-md":"","text-xs-center":""}},[o("v-form",{ref:"mainForm",attrs:{"lazy-validation":""},model:{value:t.validMainForm,callback:function(e){t.validMainForm=e},expression:"validMainForm"}},[o("Count"),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md10:"","offset-md1":""}},[o("v-divider")],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","mt-5":""}},[o("v-flex",{attrs:{xs6:"",md3:"","offset-md1":""}},[o("v-text-field",{attrs:{rules:t.dogovorNumberRules,label:"Номер договора",required:""},model:{value:t.dogovor.iddog,callback:function(e){t.$set(t.dogovor,"iddog",e)},expression:"dogovor.iddog"}})],1),t._v(" "),o("v-flex",{attrs:{xs6:"",md3:"","offset-md4":""}},[o("v-text-field",{attrs:{label:"Дата заключения",rules:[function(t){return!!t||"Обязательное поле"}],"prepend-icon":"event",readonly:""},model:{value:t.dogovor.regdate,callback:function(e){t.$set(t.dogovor,"regdate",e)},expression:"dogovor.regdate"}})],1)],1),t._v(" "),o("Client"),t._v(" "),o("Estate"),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","mt-5":""}},[o("v-flex",{attrs:{xs10:"","offset-xs1":"","text-xs-left":""}},[t._v("\n          КОММЕНТАРИЙ\n        ")])],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md10:"","offset-md1":""}},[o("v-divider")],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs3:"",md2:"","offset-md1":"","pt-5":""}},[o("v-subheader",[t._v("Комментарий к договору (не печатается на полисе)")])],1),t._v(" "),o("v-flex",{attrs:{xs9:"",md8:""}},[o("v-text-field",{attrs:{name:"input-7-1",label:"Комментарий","multi-line":""},model:{value:t.dogovor.comment,callback:function(e){t.$set(t.dogovor,"comment",e)},expression:"dogovor.comment"}})],1)],1),t._v(" "),o("v-layout",{attrs:{row:"",wrap:"","mb-5":""}},[o("v-flex",{attrs:{xs6:"","text-xs-right":"","pr-3":""}},[o("v-btn",{attrs:{disabled:t.isValidCount},on:{click:function(e){t.dogovorSave()}}},[t._v("Сохранить")])],1),t._v(" "),o("v-flex",{attrs:{xs6:"","text-xs-left":"","pl-3":""}},[o("v-btn",{on:{click:t.toTheDogovorsList}},[t._v("К списку договоров")])],1)],1)],1)],1):t._e()],1)],1),t._v(" "),o("v-footer",{staticClass:"mt-3 py-4",attrs:{app:""}},[o("v-btn",{attrs:{absolute:"",dark:"",fab:"",top:"",right:""},on:{click:function(e){t.dark=!t.dark}}},[o("v-icon",[t._v("invert_colors")])],1)],1)],1)},staticRenderFns:[]};var p=o("VU/8")(h,g,!1,function(t){o("2wno")},null,null).exports,x=o("/ocq");a.a.use(x.a);var b=new x.a({routes:[{path:"/",name:"App",component:p}]}),w=o("NYxO"),_=o("mtWM"),D=o.n(_);a.a.use(w.a);var k="http://dogov.herokuapp.com",S=new w.a.Store({state:{validCount:!1,dogovor:{},strahovatel:{},dogovors:[],strahovatels:[],showOneDogovor:!1},getters:{getValidCount:function(t){return t.validCount},getDogovor:function(t){return t.dogovor},getStrahovatel:function(t){return t.strahovatel},getAllDogovors:function(t){return t.dogovors},getShowOneDogovor:function(t){return t.showOneDogovor},getStrahovatels:function(t){return t.strahovatels}},actions:{saveStrahovatel:function(t,e){e.idstrah?D.a.post(k+"/api/updatestrahovatel/"+e.idstrah,e).catch(function(t){console.log(t)}):D.a.post(k+"/api/savestrahovatel",e).catch(function(t){console.log(t)})},saveDogovor:function(t,e){e.iddog?D.a.post(k+"/api/updatedogovor/"+e.iddog,e).catch(function(t){console.log(t)}):D.a.post(k+"/api/savedogovor",e).catch(function(t){console.log(t)})},createDogovor:function(t){t.commit("setDogovor",{}),t.commit("setStrahovatel",{})},setDogovor:function(t,e){t.commit("setDogovor",e)},setStrahovatel:function(t,e){t.commit("setStrahovatel",e)},setShowOneDogovor:function(t,e){t.commit("setShowOneDogovor",e)},getDogovors:function(t){D.a.get(k+"/api/getdogovors").then(function(e){e.data.forEach(function(t){t.startdate=t.startdate.substr(0,10),t.enddate=t.enddate.substr(0,10),t.regdate=t.regdate.substr(0,10),t.countdate=t.countdate.substr(0,10)}),t.commit("getDogovors",e.data)}).catch(function(t){console.log(t)})},setNewRegDate:function(t){t.commit("setNewRegDate")},setNewStartDate:function(t){t.commit("setNewStartDate")},setValidCount:function(t,e){t.commit("setValidCount",e)},searchDogovor:function(t,e){D.a.get(k+"/api/getdogovor/"+e).then(function(e){var o=e.data;o.startdate=e.data.startdate.substr(0,10),o.enddate=e.data.enddate.substr(0,10),o.countdate=e.data.countdate.substr(0,10),o.regdate=e.data.regdate.substr(0,10),t.commit("setDogovor",o)}).catch(function(t){console.log(t)})},getStrahovatel:function(t,e){e&&D.a.get(k+"/api/getstrahovatel/"+e).then(function(e){var o=e.data;o.birth=e.data.birth.substr(0,10),t.commit("setStrahovatel",o)}).catch(function(t){console.log(t)})},getStrahovatels:function(t,e){var o=e.split("=")[0],a=e.split("=")[1],r=e.split("=")[2];D.a.get(k+"/api/getstrahovatels/?lastname="+o+"&firstname="+a+"&firstname2="+r).then(function(e){e.data.forEach(function(t){t.birth=t.birth.substr(0,10)}),t.commit("getStrahovatels",e.data)}).catch(function(t){console.log(t)})}},mutations:{setDogovor:function(t,e){t.dogovor=e},setShowOneDogovor:function(t,e){t.showOneDogovor=e},getDogovors:function(t,e){t.dogovors=e},setNewRegDate:function(t){t.dogovor.regdate=(new Date).toISOString().substr(0,10)},setNewStartDate:function(t){t.dogovor.startdate=(new Date).toISOString().substr(0,10)},setValidCount:function(t,e){t.validCount=e},setStrahovatel:function(t,e){t.strahovatel=e},getStrahovatels:function(t,e){t.strahovatels=e}}}),y=o("M+UZ"),$=o.n(y),V=o("IcMm"),O=o.n(V),C=o("rPQa"),M=o.n(C),q=o("TWha"),I=o.n(q),R=o("fYhH"),B=o.n(R),F=o("7Q1V"),N=o.n(F),E=o("7M7f"),P=o.n(E),T=o("JUsQ"),z=o.n(T),A=o("+9ps"),L=o.n(A),U=o("IzLz"),j=o.n(U),H=o("8lPx"),Q=o.n(H),J=o("XRgG"),W=o.n(J),X=o("+1ch"),Z=o.n(X),Y=o("DBae"),G=o.n(Y),K=o("7gKz"),tt=o.n(K),et=o("DDBM"),ot=o.n(et),at=o("/yi5"),rt=o.n(at),st=o("LmeB"),nt=o.n(st),it=o("MPXs"),lt=o.n(it),vt=o("f/u0"),dt=o.n(vt),ut=o("6VHA"),ct=o.n(ut);o("s6ZO");a.a.use($.a,{components:{VApp:O.a,VNavigationDrawer:M.a,VFooter:I.a,VList:B.a,VBtn:N.a,VIcon:P.a,VGrid:z.a,VToolbar:L.a,VDatePicker:j.a,VPicker:Q.a,VDivider:W.a,VSelect:Z.a,VMenu:G.a,VSubheader:tt.a,VForm:ot.a,VTextField:rt.a,VDataTable:nt.a,VDialog:lt.a,VCard:dt.a,transitions:ct.a}}),a.a.config.productionTip=!1,new a.a({el:"#app",router:b,store:S,components:{App:p},template:"<App/>"})},NOHZ:function(t,e){},P0ba:function(t,e){},PJ2A:function(t,e){},Pjg2:function(t,e){},Stsr:function(t,e){},WxiO:function(t,e){},"X05+":function(t,e){},XC5Q:function(t,e){},acBN:function(t,e){},"d/lB":function(t,e){},hVUK:function(t,e){},jQdp:function(t,e){},kP4z:function(t,e){},kVeV:function(t,e){},lThp:function(t,e){},pu2v:function(t,e){},"q/9b":function(t,e){},qRVk:function(t,e){},rzhv:function(t,e){},s6ZO:function(t,e){},sBiC:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.dd4c1164afd4bca4e141.js.map
<template>
<div>
    <v-layout row wrap mb-3>
        <v-flex xs12 md10 offset-md1>
        <v-data-table
            v-model="selected"
            :headers="headers"
            :items="dogovors"
            item-key="iddog"
            class="elevation-1"
        >
            <template slot="items" slot-scope="props">
            <tr :active="props.selected" @click="openSelected(props.item)">
            <td>{{ props.item.iddog }}</td>
            <td class="text-xs-center">{{ props.item.regdate }}</td>
            <td class="text-xs-left">{{ props.item.fullname }}</td>
            <!-- <td class="text-xs-center">{{ fio(props.item.strahovatelid) }}</td> -->
            <td class="text-xs-right">{{ props.item.bonus }}</td>
            <td class="text-xs-center">{{ props.item.startdate }} - {{ props.item.enddate }}</td>
            </tr>
            </template>
        </v-data-table>
        </v-flex>
    </v-layout>
    <v-layout row wrap mb-3>
        <v-flex xs12 md10 offset-md1>

        </v-flex>
    </v-layout>
</div>
</template>

<script>
  export default {
    name: 'Dtable',
    props: {
      selected: null
    },
    data () {
      return {
        headers: [
          {
            text: 'Серия-номер',
            align: 'center',
            sortable: false,
            value: 'iddoc'
          },
          { text: 'Дата заключения', align: 'center', value: 'regdate' },
          { text: 'Страхователь', align: 'left', value: 'fullname' },
          { text: 'Премия', align: 'right', value: 'bonus' },
          { text: 'Срок действия', align: 'center', value: 'dates' }
        ]
      }
    },
    computed: {
      dogovors () {
        return this.$store.getters.getAllDogovors
      }
    },
    methods: {
      openSelected (item) {
        // alert('Clicked ' + item.iddog)
        this.$store.dispatch('setDogovor', item)
        this.$store.dispatch('setShowOneDogovor', true)
      },
      fio (item) {
        console.log(item)
        this.$store.dispatch('getStrahovatel', item)
        var strahovatel = this.$store.getters.getStrahovatel
        console.log(strahovatel)
        return strahovatel.lastname + ' ' + strahovatel.firstname + ' ' + strahovatel.firstname2
      }
    },
    created () {
      this.$store.dispatch('getDogovors')
    }
  }
</script>

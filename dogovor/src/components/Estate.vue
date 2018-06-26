<template>
    <div>
        <v-layout row wrap mt-5>
            <v-flex xs10 offset-xs1 text-xs-left>
                АДРЕС НЕДВИЖИМОСТИ
            </v-flex>
        </v-layout>
        <v-layout row wrap>
            <v-flex xs12 md10 offset-md1>
                <v-divider></v-divider>
            </v-flex>
        </v-layout>
        <v-layout row wrap mt-3>
            <v-flex xs6 md2 offset-md1>
                <v-select
                v-model="dogovor.state"
                :items="states"
                :rules="[v => !!v || 'Обязательное поле']"
                label="Государство"
                required
                ></v-select>
            </v-flex>
            <v-flex xs6 md1>
                <v-text-field
                v-model="dogovor.index"
                label="Индекс"
                ></v-text-field>
            </v-flex>
            <v-flex xs6 md4>
                <v-text-field
                v-model="dogovor.krai"
                label="Республика, край, область"
                :rules="[v => !!v || 'Обязательное поле']"
                required
                ></v-text-field>
            </v-flex>
            <v-flex xs6 md3>
                <v-text-field
                v-model="dogovor.district"
                label="Район"
                ></v-text-field>
            </v-flex>
        </v-layout>   
        <v-layout row wrap>
            <v-flex xs6 md2 offset-md1>
                <v-text-field
                v-model="dogovor.town"
                label="Населенный пункт"
                :rules="[v => !!v || 'Обязательное поле']"
                required
                ></v-text-field>
            </v-flex>
            <v-flex xs6 md4>
                <v-text-field
                v-model="dogovor.street"
                label="Улица"
                :rules="[v => !!v || 'Обязательное поле']"
                required
                ></v-text-field>
            </v-flex>
            <v-flex xs3 md1>
                <v-text-field
                v-model="dogovor.house"
                label="Дом"
                :rules="[v => !!v || 'Обязательное поле']"
                required
                ></v-text-field>
            </v-flex>
            <v-flex xs3 md1>
                <v-text-field
                v-model="dogovor.korpus"
                label="Корпус"
                ></v-text-field>
            </v-flex>
            <v-flex xs3 md1>
                <v-text-field
                v-model="dogovor.stroenie"
                label="Строение"
                ></v-text-field>
            </v-flex>
            <v-flex xs3 md1>
                <v-text-field
                v-model="dogovor.flat"
                label="Квартира"
                :rules="[v => !!v || 'Обязательное поле']"
                required
                ></v-text-field>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
export default {
  name: 'Estate',
  data () {
    return {
      states: [
        'Россия',
        'Белоруссия',
        'Казахстан'
      ]
    }
  },
  computed: {
    dogovor () {
      return this.$store.getters.getDogovor
    }
  }
}
</script>

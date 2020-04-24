import {Module} from "vuex";

export const store: Module<StoreState, any> = {
    namespaced: true,
    state: {
        current: null
    },
    getters: {},
    mutations: {
        current(s: StoreState, field: Field) {
            s.current = field
        }
    },
    actions: {}
}


package com.prilepskiy.thousandsofcourses.common

interface Reducer<A : MviAction, S : MviState> {
    fun reduce(action: A, state: S): S
}
package com.rifafauzi.footballmatch.utils

import org.mockito.Mockito

/**
 * Created by rrifafauzikomara on 2019-12-02.
 */

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
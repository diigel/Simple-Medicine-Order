package com.dani.simplemedicineorder.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dani.simplemedicineorder.data.Service
import com.dani.simplemedicineorder.repository.MedicineOrderRepositoryImpl
import com.dani.simplemedicineorder.utils.*
import com.dani.simplemedicineorder.utils.mockResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MedicineOrderViewModelTest {

    @get : Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get : Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel : MedicineOrderViewModel
    private lateinit var service : Service

    private val mockWebServer = MockWebServer()

    @Before
    fun `setup before` () {
        service = ServiceMock.create(mockWebServer.url("/"))
        val repository = MedicineOrderRepositoryImpl(service)
        viewModel = MedicineOrderViewModel(repository)
    }

    @After
    fun `setup after` (){
        mockWebServer.shutdown()
    }

    @Test
    fun `medicine order list should be success` () = runBlocking {
        mockWebServer.mockResponse("medicine-order-list-success-response",200)

        viewModel.medicineOrderList()
        val actualResult = viewModel.medicineOrderList.getOrAwaitValue()
        val actual = actualResult.getDataOrNull()
        val expectation = listOf(
            Constant.medicineOrder1,
            Constant.medicineOrder2,
            Constant.medicineOrder3
        )

        println("start result medicine order list ------")
        println(actual?.toJson())
        println("end result medicine order list ------")
        assertEquals(expectation, actual)
    }

    @Test
    fun `medicine order detail should be success` () = runBlocking {
        mockWebServer.mockResponse("medicine-order-detail-success-response",200)

        viewModel.medicineOrderDetail(5)
        val actualResult = viewModel.medicineOrderDetail.getOrAwaitValue()
        val actual = actualResult.getDataOrNull()
        val expectation = Constant.medicineOrderDetail

        println("start result medicine order list ------")
        println(actual?.toJson())
        println("end result medicine order list ------")
        assertEquals(expectation, actual)
    }
}
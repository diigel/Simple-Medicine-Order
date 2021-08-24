package com.dani.simplemedicineorder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MedicineOrderListRepositoryTest {

    @get : Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get : Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: MedicineOrderRepository
    private lateinit var service : Service

    private val mockWebServer = MockWebServer()

    @Before
    fun `setup before` () {
        service = ServiceMock.create(mockWebServer.url("/"))
        repository = MedicineOrderRepositoryImpl(service)
    }

    @After
    fun `setup after` (){
        mockWebServer.shutdown()
    }


    @Test
    fun `medicine order list should be success` ()  = runBlocking {
        mockWebServer.mockResponse("medicine-order-list-success-response",200)

        repository.getMedicineOrderList()

        val actual = repository.medicineOrderList.first().getDataOrNull()

        val expectation = listOf(Constant.medicineOrder1,Constant.medicineOrder2,Constant.medicineOrder3)

        println("start result medicine order list ------")
        println(actual?.toJson())
        println("end result medicine order list ------")
        assertEquals(expectation,actual)
    }

    @Test
    fun `medicine order detail should be success` () = runBlocking {
        mockWebServer.mockResponse("medicine-order-detail-success-response",200)

        repository.getMedicineOrderDetail(5)

        val actual = repository.medicineOrderDetail.first().getDataOrNull()
        val expectation = Constant.medicineOrderDetail

        println("start result medicine order list ------")
        println(actual?.toJson())
        println("end result medicine order list ------")
        assertEquals(expectation,actual)
    }
}
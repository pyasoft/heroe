package com.aac.superheroe

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

/*
    @Test
    fun testAddingAndRetrievingData() {
        // 1
        val preInsertRetrievedCategories = listCategoryDao.getAll()

        // 2
        val listCategory = ListCategory("Cats", 1)
        listCategoryDao.insertAll(listCategory)

        //3
        val postInsertRetrievedCategories = listCategoryDao.getAll()
        val sizeDifference = postInsertRetrievedCategories.size - preInsertRetrievedCategories.size
        Assert.assertEquals(1, sizeDifference)
        val retrievedCategory = postInsertRetrievedCategories.last()
        Assert.assertEquals("Cats", retrievedCategory.categoryName)
    }
    */
}

package kst.app.fcdelivery.data.repository

import kst.app.fcdelivery.data.entity.ShippingCompany

interface ShippingCompanyRepository {

    suspend fun getShippingCompanies(): List<ShippingCompany>
    suspend fun getRecommendShippingCompany(invoice: String): ShippingCompany?
}

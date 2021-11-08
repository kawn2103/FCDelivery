package kst.app.fcdelivery.data.entity
import com.google.gson.annotations.SerializedName

data class ShippingCompanies(
    //alternate 동일한 데이터이나 다른 이름으로 받을 일이 생겼을 때 사용하는 방법
    @SerializedName("Company", alternate = ["Recommend"])
    val shippingCompanies: List<ShippingCompany>? = null
)

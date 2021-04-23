import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping("/products/{id}/price")
    fun getPrice(@PathVariable id:String) = ResponseEntity.ok(
            ProductPrice(
                    id = "993.283.34",
                    name = "APPLARO",
                    price = "179.83",
                    currency = "EUR"
            ))
}

data class ProductPrice(
        val id: String,
        val name: String,
        val price: String,
        val currency: String
)
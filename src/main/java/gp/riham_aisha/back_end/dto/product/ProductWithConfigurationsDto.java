package gp.riham_aisha.back_end.dto.product;

import gp.riham_aisha.back_end.model.product_and_configuration.Configuration;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;

import java.util.List;

//  ----------------------------------------------------------------
// | This Dto is used to represent a product with its features only |
// | mainly used for adding and updating products                   |
//  ----------------------------------------------------------------
// TODO: add lists of features
public record ProductWithConfigurationsDto(Product product, Long storeId,
                                           List<Configuration> configurations, Long categoryId) {

}

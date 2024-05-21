import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/Product';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
    public term : string
    public products : Product[]

    constructor(private router : Router, route : ActivatedRoute, private productsService : ProductsService) {
        this.term = route.snapshot.paramMap.get('term') || ""
    }

    ngOnInit(): void {
        this.productsService.getProducts().subscribe((products : Product[]) => {
            this.products = products

            for (let product of this.products) {
                product.url = product.url!==null ? product.url :
                "../../../assets/static/images/product-placeholder.png";
                console.log(product.url);
            }
        }, (error: ErrorEvent) => {
          console.log("error is there")
        })
    }

    openAddProduct() {
      this.router.navigate(['/add-product']);
    }
}

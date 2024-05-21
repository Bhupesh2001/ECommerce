import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/Product';
import { ProductsService } from 'src/app/services/products.service';
import { Validators, FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
  inputs: [],
})
export class AddProductComponent implements OnInit {
  public product: Product = new Product();
  public error: string;
  public success: string;

  productForm: FormGroup;

  constructor(
    private router: Router,
    private productsService: ProductsService
  ) {
    this.productForm = new FormGroup({
      name: new FormControl(this.product.name, [
        Validators.required,
        Validators.minLength(2),
      ]),
      price: new FormControl(this.product.price, [
        Validators.required,
        Validators.min(0),
      ]),
      description: new FormControl(this.product.description, [
        Validators.required,
      ]),
      image: new FormControl(this.product.url, [Validators.required]),
    });
  }

  ngOnInit(): void {}

  addProduct(): void {
    if (this.productForm.valid) {
      this.product = new Product();
      this.productsService.postProduct(this.product).subscribe({
        next: (reponse: Product) => {
          this.success = 'New Product Added';
          this.product = new Product();
          console.log('New Product Added ' + reponse);
        },
        error: (error) => {
          this.error = error;
          console.log('Error occured ' + error);
        },
      });
    } else {
      this.error = 'Fill the form properly';
    }
  }
}

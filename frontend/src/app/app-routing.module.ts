import { OrderHistoryDetailComponent } from './components/order-history-detail/order-history-detail.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { LoginComponent } from './components/login/login.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { RegisterComponent } from './components/register/register.component';
import { UserDetailComponent } from './components/user-detail/user-detail.component';
import { OrderHistoryComponent } from './components/order-history/order-history.component';

const routes: Routes = [
    {path: '', redirectTo: '/login', pathMatch: 'full'},
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'shop', component: ProductListComponent},
    {path: 'shop/:term', component: ProductListComponent},
    {path: 'shop/products/:id', component: ProductDetailComponent},
    {path: 'account', component: UserDetailComponent},
    {path: 'cart', component: CartComponent},
    {path: 'add-product', component: AddProductComponent},
    {path: 'order-history', component: OrderHistoryComponent},
    {path: 'order-history-details/:orderId', component: OrderHistoryDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
    constructor (router : Router) {}
}

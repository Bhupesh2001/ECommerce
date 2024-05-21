import { OrderHistory } from './../../models/OrderHistory';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faCaretUp, faCaretDown } from '@fortawesome/free-solid-svg-icons';
import { CartItem } from 'src/app/models/CartItem';
import { User } from 'src/app/models/User';
import { CartItemsService } from 'src/app/services/cart-items.service';
import { UsersService } from 'src/app/services/users.service';
import { OrderHistoryDetails } from 'src/app/models/OrderHistoryDetails';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
  providers: [OrderHistoryDetails],
})
export class CartComponent implements OnInit {
  caretUp = faCaretUp;
  caretDown = faCaretDown;
  public error: string;
  public success: string;
  user: User;
  cartItems: CartItem[];
  details: OrderHistoryDetails[] = [];
  detail: OrderHistoryDetails;

  constructor(
    private router: Router,
    private usersService: UsersService,
    private cartItemsService: CartItemsService
  ) {}

  ngOnInit(): void {
    if (!localStorage.getItem('token')) {
      this.router.navigateByUrl('/login');
      return;
    }

    this.usersService.getUserByToken().subscribe(
      (user: User) => {
        this.user = user;
        this.getItems();
      },
      (error: ErrorEvent) => {
        console.log(error);
      }
    );
  }

  getItems() {
    this.cartItemsService
      .getUserCart(this.user.id.toString())
      .subscribe((cartItems: CartItem[]) => {
        this.cartItems = cartItems;
      });
  }

  getTotal(): number {
    var reducer = (acc, val) => acc + val;
    return this.cartItems
      ? this.cartItems.map((item) => item.totalPrice).reduce(reducer)
      : 0.0;
  }

  increaseQuantity(item: CartItem) {
    this.cartItemsService
      .updateUserCartItem(
        this.user.id.toString(),
        item.product.id.toString(),
        item.quantity + 1
      )
      .subscribe((res) => {
        console.log(res);
        this.getItems();
      });
  }

  decreaseQuantity(item: CartItem) {
    if (item.quantity - 1 <= 0) {
      this.cartItemsService
        .deleteUserCartItem(this.user.id.toString(), item.product.id.toString())
        .subscribe((res) => {
          console.log(res);
          this.getItems();
        });
    } else {
      this.cartItemsService
        .updateUserCartItem(
          this.user.id.toString(),
          item.product.id.toString(),
          item.quantity - 1
        )
        .subscribe((res) => {
          console.log(res);
          this.getItems();
        });
    }
    this.getTotal();
  }

  placeOrder(): void {
    if (this.cartItems.length > 0) {
      const order: OrderHistory = {
        userId: this.user.id,
        totalAmount: this.getTotal(),
      };

      this.addToOrderHistory(order);
      this.success="Order Placed Successfuly"
    } else {
      this.error="Cart is empty "
    }
  }

  clearCart(){
    this.cartItems.forEach(item =>{
      this.cartItemsService
        .deleteUserCartItem(this.user.id.toString(), item.product.id.toString())
        .subscribe();
    })
    this.getTotal();
  }

  addToOrderHistory(order: OrderHistory) {
    this.cartItemsService.addToOrderHistory(order).subscribe({
      next: (response: OrderHistory) => {
        this.cartItems.forEach((item) => {
          this.detail = {
            orderId: response.orderId,
            price: item.product.price,
            quantity: item.quantity,
            productName: item.product.name,
          };
          this.details.push(this.detail);
        });
        this.addToOrderHistoryDetails();
      },
      error: (error: ErrorEvent) => {
        this.error = error.message;
        console.log('Error occured in addToOrderHistory' + error.message);
      },
    });
  }

  addToOrderHistoryDetails(): void {
    console.log(this.details);

    this.cartItemsService.addToOrderHistoryDetails(this.details).subscribe({
      next: (response: any) => {
        console.log(
          'response of addToOrderHistory ' + JSON.stringify(response)
        );
        this.clearCart();
        this.cartItems = [];
      },
      error: (error: ErrorEvent) => {
        this.error = error.message;
        console.log('Error occured addToOrderHistoryDetails' + error.message);
      },
    });
  }
}

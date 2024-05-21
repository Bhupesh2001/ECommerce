import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderHistory } from 'src/app/models/OrderHistory';
import { OrderHistoryDetails } from 'src/app/models/OrderHistoryDetails';
import { User } from 'src/app/models/User';
import { OrderHistoryService } from 'src/app/services/order-history.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css'],
})
export class OrderHistoryComponent implements OnInit {
  orderHistories: OrderHistory[];
  user: User;

  constructor(
    private router: Router,
    private usersService: UsersService,
    private orderHistoryService: OrderHistoryService
  ) {}

  ngOnInit() {
    if (!localStorage.getItem('token')) {
      this.router.navigateByUrl('/login');
      return;
    }

    this.usersService.getUserByToken().subscribe(
      (user: User) => {
        this.user = user;
        this.getAllOrders(user.id);
      },
      (error: ErrorEvent) => {
        console.log(error);
      }
    );
  }

  getAllOrders(userId: number) {
    this.orderHistoryService.getAllOrders(userId).subscribe((orders) => {
      this.orderHistories = orders.reverse();
    });
  }
  showDetails(orderId:number){
    this.router.navigateByUrl(`/order-history-details/${orderId}`);
  }
}

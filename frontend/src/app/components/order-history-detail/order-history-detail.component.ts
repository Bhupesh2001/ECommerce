import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderHistoryDetails } from 'src/app/models/OrderHistoryDetails';
import { OrderHistoryService } from 'src/app/services/order-history.service';

@Component({
  selector: 'app-order-history-detail',
  templateUrl: './order-history-detail.component.html',
  styleUrls: ['./order-history-detail.component.css'],
})
export class OrderHistoryDetailComponent implements OnInit {
  orderDetails: OrderHistoryDetails[] = [];
  total = 0; // Initialize total as a number and set it to 0

  constructor(
    private route: ActivatedRoute,
    private orderHistoryService: OrderHistoryService
  ) {}

  ngOnInit(): void {
    let orderId: number = parseInt(this.route.snapshot.paramMap.get('orderId'));
    this.getOrderDetails(orderId);

  }

  getOrderDetails(orderId: number) {
    this.orderHistoryService
      .getOrderDetailsByOrderId(orderId)
      .subscribe((details) => {
        this.orderDetails = details;
        this.getTotal();
      });
  }

  getTotal() {
    this.orderDetails.forEach(detail => {
      this.total += detail.price.valueOf() * detail.quantity.valueOf();
    })
  }
}

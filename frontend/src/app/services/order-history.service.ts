import { OrderHistoryDetails } from 'src/app/models/OrderHistoryDetails';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrderHistory } from '../models/OrderHistory';

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {
  constructor(private http: HttpClient) {}

  getAllOrders(userId:number) : Observable<OrderHistory[]>{
    return this.http.get<OrderHistory[]>(
      `${environment.API_URL}/api/order-history/${userId}`
    );
  }

  getOrderDetailsByOrderId(orderId:number):Observable<OrderHistoryDetails[]>{
    return this.http.get<OrderHistoryDetails[]>(
      `${environment.API_URL}/api/order-history-detail/${orderId}`
    );
  }
}

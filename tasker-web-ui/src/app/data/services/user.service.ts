import { Injectable } from '@angular/core';
import { CRUDService } from './crudservice';
import { User } from '@data/domain-model/user.type';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService extends CRUDService<User, BigInt> {

  constructor(private httpClient: HttpClient) {
    super("http://localhost:8080/tasker/v1/user", httpClient);
   }
}

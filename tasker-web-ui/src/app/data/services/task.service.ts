import { Injectable } from '@angular/core';
import { CRUDService } from './crudservice';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Task } from "@data/domain-model/task.type";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService extends CRUDService<Task, BigInt> {

  constructor(private httpClient: HttpClient) {
    super("http://localhost:8080/tasker/v1/task", httpClient);
  }

  getTaskFilteredByUser(userId:string):Observable<Task[]>{
    const params = userId 
    ? {
      params: new HttpParams().set('assignedTo', userId)
    }: {};
    return this.httpClient.get<Task[]>(`${this.url}`, params);
  }

  startTask(taskId:BigInt):Observable<Task>{
    return this.httpClient.post<Task>(`${this.url}/${taskId}/start`, {});
  }

  doneTask(taskId:BigInt):Observable<Task>{
    return this.httpClient.post<Task>(`${this.url}/${taskId}/done`, {});
  }

  pendingTask(taskId:BigInt):Observable<Task>{
    return this.httpClient.post<Task>(`${this.url}/${taskId}/pending`, {});
  }

}

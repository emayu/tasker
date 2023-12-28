import { Component, OnInit } from '@angular/core';
import { Task } from '@data/domain-model/task.type';
import { User } from '@data/domain-model/user.type';
import { TaskService } from '@data/services/task.service';
import { UserService } from '@data/services/user.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  taskList : Task[] = [];
  userList : User[] = [];

  constructor(private taskService:TaskService, private userService:UserService){}

  ngOnInit(): void {
    this.getAllTask();
    this.getAllUser();
  }

  filterPending():Task[] {
    return this.taskList.filter( i => i.status === "PENDING");
  }

  filterInProgress():Task[]{
    return this.taskList.filter( i => i.status === 'IN_PROGRESS');
  }

  filterDone():Task[]{
    return this.taskList.filter( i => i.status === 'DONE');
  }

  getAllTask(){
    this.taskService.getAll()
    .subscribe(
      response => this.taskList = response
    );
  }
  
  getAllUser(){
    this.userService.getAll()
    .subscribe(
      response => this.userList = response
    )
  }

  onSelectUser(user:string){
    this.taskService.getTaskFilteredByUser(user)
    .subscribe(
      response => this.taskList = response
    )
  }

}

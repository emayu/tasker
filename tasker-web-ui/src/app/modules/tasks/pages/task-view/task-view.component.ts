import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Task, TaskStatus } from '@data/domain-model/task.type';
import { TaskService } from '@data/services/task.service';

@Component({
  selector: 'task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent {
  
  readonly TaskStatus = TaskStatus;
  
  @Input()
  taskInfo:Task = {
    id: null,
    description : "",
    status : TaskStatus.IN_PROGRESS,
    title : "",
    assignedTo: null,
    placedAt : new Date()
  };

  @Input()
  globalTaskList:Task[] = [];

  @Output('onUserAssign') onUserAssign = new EventEmitter<Task>();
  @Output('onClickEditTask') onClickEditTask = new EventEmitter<Task>();

  constructor(private taskService:TaskService){}

  deleteTask(){
    if(this.taskInfo.id){
      //this is for better ui experience
      let index = this.globalTaskList.indexOf(this.taskInfo);
      this.globalTaskList.splice(index, 1);
      this.taskService
      .delete(this.taskInfo.id)
      .subscribe( 
        {
          error : error => {
            this.globalTaskList.splice(index, 0, this.taskInfo);
            console.log("error", error);
          }
        }
      )
    }
  }

  toStartTask(){
    if(this.taskInfo.id){
      let index = this.globalTaskList.indexOf(this.taskInfo);
      this.taskService
        .startTask(this.taskInfo.id)
        .subscribe(
          {
            next: taskResponse => {
              this.globalTaskList.splice(index, 1, taskResponse);
            },
            error: e => console.log(e)
          }
        )
    }
  }

  toDoneTask(){
    if(this.taskInfo.id){
      let index = this.globalTaskList.indexOf(this.taskInfo);
      this.taskService
        .doneTask(this.taskInfo.id)
        .subscribe(
          {
            next : taskResponse => {
              this.globalTaskList.splice(index, 1, taskResponse);
            },
            error :error => {
              console.log("error", error);
            }
          }
        )
    }
  }

  toPedingTask(){
    if(this.taskInfo.id){
      let index = this.globalTaskList.indexOf(this.taskInfo);
      this.taskService
        .pendingTask(this.taskInfo.id)
        .subscribe(
          taskResponse => {
            this.globalTaskList.splice(index, 1, taskResponse);
          }
        )
    }
  }

  assignUser(){
    this.onUserAssign.emit(this.taskInfo);
  }

  edit(){
    this.onClickEditTask.emit(this.taskInfo);
  }
}

import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'
export abstract class CRUDService<T, ID> {
    
    constructor(protected url:string, private http:HttpClient){}
    
    getAll():Observable<T[]>{
        return this.http.get<T[]>(this.url);
    }
    get(id:ID):Observable<T>{
        return this.http.get<T>(`${this.url}/${id}`);
    }
    save(o:T):Observable<T>{
        return this.http.post<T>(this.url, o);
    }
    edit(o:T, id:ID):Observable<T>{
        return this.http.put<T>(`${this.url}/${id}`, o);
    }
    delete(id:ID): Observable<Object>{
        return this.http.delete(`${this.url}/${id}`);
    }
}

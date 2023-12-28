import { User } from '@data/domain-model/user.type';
export type Task = {
    id:BigInt|null;
    title: string;
    description:string;
    assignedTo: User | null;
    status:string;
    placedAt:Date;
}
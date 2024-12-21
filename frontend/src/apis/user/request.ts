import api from '../axiosClient';
import { GetUserResponse, GetUsersResponse } from './type';

export const getUsers = async (): Promise<GetUsersResponse> => {
  try {
    const response = await api.get('/users');
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export const getUser = async (id: string): Promise<GetUserResponse> => {
  try {
    const response = await api.get(`/users/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

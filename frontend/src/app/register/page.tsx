'use client';

import { register, RegisterRequest } from '@/apis/auth';
import { Button, DateInput, DatePicker, Input } from '@nextui-org/react';
import { CalendarDate } from '@internationalized/date';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import { toast } from 'react-hot-toast';

const Register = () => {
  const [formData, setFormData] = useState<RegisterRequest>({
    firstName: '',
    lastName: '',
    birthday: '',
    username: '',
    password: '',
    confirmPassword: '',
  });
  const [error, setError] = useState<string | null>(null);
  const router = useRouter();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const data = await register(formData);

      toast.success('Registration successful!');
      router.push('/login');
    } catch (err) {
      setError('Registration failed');
      toast.error('Registration failed. Please try again.');
    }
  };

  return (
    <div className="flex h-screen">
      <div className="w-1/2 flex justify-center items-center bg-blue-50">
        <div className="w-[550px] px-6">
          <h2 className="text-3xl font-bold text-blue-600 mb-10 text-center">Login</h2>

          {error && <small className="text-red-500 text-center block mb-4">{error}</small>}

          <div className="flex gap-6">
            <Input
              fullWidth
              label="First Name"
              className="mb-6"
              color="primary"
              aria-label="firstName"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
            />
            <Input
              fullWidth
              label="Last Name"
              className="mb-6"
              color="primary"
              aria-label="lastName"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
            />
          </div>

          <Input
            fullWidth
            label="Username"
            className="mb-6"
            color="primary"
            aria-label="username"
            name="username"
            value={formData.username}
            onChange={handleChange}
          />
          {/* <DatePicker
            label="Birth date"
            className="mb-6"
            color="primary"
            placeholderValue={new CalendarDate(1995, 11, 6)}
          /> */}
          <Input
            fullWidth
            label="Birthday"
            className="mb-6"
            color="primary"
            aria-label="birthday"
            name="birthday"
            value={formData.birthday}
            onChange={handleChange}
            type="date"
          />
          <Input
            fullWidth
            className="mb-6"
            color="primary"
            label="Password"
            aria-label="Password"
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
          <Input
            className="mb-6"
            color="primary"
            fullWidth
            label="Confirm Password"
            aria-label="Confirm Password"
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
          />

          <Button
            onPress={handleSubmit}
            color="primary"
            size="lg"
            fullWidth
            aria-label="Register"
            className="bg-blue-600 text-white hover:bg-blue-700 focus:outline-none"
          >
            Register
          </Button>

          <div className="mt-4 text-center">
            Have an account?
            <Link href="/login" className="text-blue-600 hover:underline">
              Login
            </Link>
          </div>
        </div>
      </div>

      <div className="w-1/2 bg-blue-500 flex justify-center items-center bg-[url('/images/image_form.png')] bg-cover bg-no-repeat bg-center"></div>
    </div>
  );
};

export default Register;

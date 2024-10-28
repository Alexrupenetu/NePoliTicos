module fac(
  input a, b ,cin,
  output s, cout
);

assign {cout, s} = a + b + cin;

endmodule


module add2b(
  input cin2,
  input [1:0]x, y,
  output cout2,
  output [1:0] s2
);

  wire f;
  
  
  fac fac1(
    .a(x[0]),
    .b(y[0]),
    .cin(cin2),
    .cout(f),
    .s(s2[0])
  );
  
  
  fac fac2(
    .a(x[1]),
    .b(y[1]),
    .cin(f),
    .cout(cout2),
    .s(s2[1])
  );
  
endmodule


module add2b_tb;
  reg [1:0] x, y;
  reg cin2;
  wire cout2;
  wire [1:0] s2;
  
  add2b add2b_i(
    .x(x),
    .y(y),
    .cin2(cin2),
    .cout2(cout2),
    .s2(s2)
  );
  
  integer i;
  
  initial begin
    {x, y, cin2} = 0;
    $display("Time\tx\ty\tcin\ts\tcout\t");
    $monitor("%0t\t%b\t%b\t%b\t%b\t%b\t", $time, x, y, cin2, s2, cout2);
    
     for(i=0; i<32; i=i+1) begin
        {x, y, cin2} = i;
        #10;
      end
  end
  
endmodule



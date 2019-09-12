using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TelefoneUsuariosController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public TelefoneUsuariosController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/TelefoneUsuarios
        [HttpGet]
        public IEnumerable<TelefoneUsuario> GetTelefoneUsuario()
        {
            return _context.TelefoneUsuario;
        }

        // GET: api/TelefoneUsuarios/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetTelefoneUsuario([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var telefoneUsuario = await _context.TelefoneUsuario.FindAsync(id);

            if (telefoneUsuario == null)
            {
                return NotFound();
            }

            return Ok(telefoneUsuario);
        }

        // PUT: api/TelefoneUsuarios/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTelefoneUsuario([FromRoute] int id, [FromBody] TelefoneUsuario telefoneUsuario)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != telefoneUsuario.CdTelefone)
            {
                return BadRequest();
            }

            _context.Entry(telefoneUsuario).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TelefoneUsuarioExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/TelefoneUsuarios
        [HttpPost]
        public async Task<IActionResult> PostTelefoneUsuario([FromBody] TelefoneUsuario telefoneUsuario)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.TelefoneUsuario.Add(telefoneUsuario);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTelefoneUsuario", new { id = telefoneUsuario.CdTelefone }, telefoneUsuario);
        }

        // DELETE: api/TelefoneUsuarios/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTelefoneUsuario([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var telefoneUsuario = await _context.TelefoneUsuario.FindAsync(id);
            if (telefoneUsuario == null)
            {
                return NotFound();
            }

            _context.TelefoneUsuario.Remove(telefoneUsuario);
            await _context.SaveChangesAsync();

            return Ok(telefoneUsuario);
        }

        private bool TelefoneUsuarioExists(int id)
        {
            return _context.TelefoneUsuario.Any(e => e.CdTelefone == id);
        }
    }
}